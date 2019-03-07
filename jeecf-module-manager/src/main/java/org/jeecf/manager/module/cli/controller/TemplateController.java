package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.gen.model.GenParams;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.DownloadUtils;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.jeecf.manager.common.utils.TemplateUtils;
import org.jeecf.manager.gen.utils.GenUtils;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.model.TemplateCodeInput;
import org.jeecf.manager.module.cli.model.TemplateField;
import org.jeecf.manager.module.cli.model.TemplateInput;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.extend.service.SysOsgiPluginService;
import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.manager.module.template.model.po.GenFieldColumnPO;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.po.GenTemplatePO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.result.GenFieldColumnResult;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.jeecf.manager.module.template.model.result.GenTemplateResult;
import org.jeecf.manager.module.template.service.GenFieldColumnService;
import org.jeecf.manager.module.template.service.GenFieldService;
import org.jeecf.manager.module.template.service.GenTemplateService;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.osgi.enums.BoundleEnum;
import org.jeecf.osgi.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 命令行模版接口
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/tmpl" })
@Api(value = "命令行模版 api", tags = { "命令行模版接口" })
public class TemplateController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private GenTemplateService genTemplateService;

    @Autowired
    private SysNamespaceService sysNamespaceService;

    @Autowired
    private SysOsgiPluginService sysOsgiPluginService;

    @Autowired
    private GenFieldService genFieldService;

    @Autowired
    private GenFieldColumnService genFieldColumnService;

    private static final int RANDOM_MAX = 4;

    private static final String DEFAULT_VERSION = "1.0.0";

    private static final Integer DEFAULT_LANGUAGE = LanguageEnum.JAVA.getCode();

    private static final String CACHE_TMPL_PREFIX = "cli:tmpl:";

    private static final String CACHE_CODE_PREFIX = "cli:code:";

    @PostMapping(value = { "list/{namespace}" })
    @ApiOperation(value = "列表", notes = "返回用户模版列表")
    public Response<List<String>> list(@RequestBody AuthModel authModel, @PathVariable String namespace) {
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(namespace);
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            List<String> fieldList = new ArrayList<>();
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(authModel, sysNamespaceResult.getPermission());
            GenTemplateQuery genTemplateQuery = new GenTemplateQuery();
            genTemplateQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
            GenTemplatePO genTemplatePO = new GenTemplatePO(genTemplateQuery);
            Response<List<GenTemplateResult>> genTemplateResultListRes = genTemplateService.findList(genTemplatePO);
            if (CollectionUtils.isNotEmpty(genTemplateResultListRes.getData())) {
                genTemplateResultListRes.getData().forEach(genTemplateResult -> {
                    fieldList.add(genTemplateResult.getName());
                });
            }
            return new Response<>(fieldList);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    @PostMapping(value = { "pull/{namespace}/{name}" })
    @ApiOperation(value = "下载", notes = "下载模版")
    public Response<String> pull(@RequestBody AuthModel authModel, @PathVariable String namespace, @PathVariable String name, HttpServletResponse response) {
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(namespace);
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(authModel, sysNamespaceResult.getPermission());
            GenTemplateQuery genTemplateQuery = new GenTemplateQuery();
            genTemplateQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
            genTemplateQuery.setName(name);
            GenTemplatePO genTemplatePO = new GenTemplatePO(genTemplateQuery);
            Response<List<GenTemplateResult>> genTemplateResultListRes = genTemplateService.findList(genTemplatePO);
            if (CollectionUtils.isNotEmpty(genTemplateResultListRes.getData())) {
                GenTemplateResult genTemplateResult = genTemplateResultListRes.getData().get(0);
                String zipFilePath = TemplateUtils.getZipFilePath(genTemplateResult.getFileBasePath(), sysNamespaceResult.getName());
                String uuid = IdGenUtils.randomUUID(RANDOM_MAX);
                RedisCacheUtils.setSysCache(CACHE_TMPL_PREFIX + uuid, zipFilePath);
                return new Response<>(uuid);
            }
            throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    @PostMapping(value = { "gen" })
    @ApiOperation(value = "当前", notes = "返回用户当前数据源名称")
    public Response<String> code(@RequestBody TemplateCodeInput templateCodeInput, HttpServletRequest request, HttpServletResponse response) {
        String id = userAuthService.login(templateCodeInput.getUsername(), templateCodeInput.getPassword(), request, response);
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(templateCodeInput.getNamespace());
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(id, sysNamespaceResult.getPermission());
            GenTemplateQuery genTemplateQuery = new GenTemplateQuery();
            genTemplateQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
            genTemplateQuery.setName(templateCodeInput.getName());
            GenTemplatePO genTemplatePO = new GenTemplatePO(genTemplateQuery);
            Response<List<GenTemplateResult>> genTemplateResultListRes = genTemplateService.findList(genTemplatePO);
            if (CollectionUtils.isNotEmpty(genTemplateResultListRes.getData())) {
                GenTemplateResult genTemplateResult = genTemplateResultListRes.getData().get(0);
                List<GenParams> paramsList = new ArrayList<>();
                if (genTemplateResult.getGenFieldId() != null) {
                    List<TemplateField> templateFieldList = templateCodeInput.getTemplateFields();
                    GenFieldColumnQuery genFieldColumnQuery = new GenFieldColumnQuery();
                    genFieldColumnQuery.setGenFieldId(genTemplateResult.getGenFieldId());
                    GenFieldColumnPO genFieldColumnPO = new GenFieldColumnPO(genFieldColumnQuery);
                    Response<List<GenFieldColumnResult>> genFieldColumnResultListRes = genFieldColumnService.findList(genFieldColumnPO);
                    Map<String, String> fieldMap = new HashMap<>(12);
                    if (CollectionUtils.isNotEmpty(genFieldColumnResultListRes.getData())) {
                        genFieldColumnResultListRes.getData().forEach(genFieldColumnResult -> {
                            fieldMap.put(genFieldColumnResult.getName(), genFieldColumnResult.getDefaultValue());
                        });
                    }
                    if (CollectionUtils.isNotEmpty(templateFieldList)) {
                        templateFieldList.forEach(templateField -> {
                            fieldMap.put(templateField.getKey(), templateField.getValue());
                        });
                    }
                    fieldMap.forEach((key, value) -> {
                        GenParams params = new GenParams();
                        params.setName(key);
                        params.setValue(value);
                        paramsList.add(params);
                    });
                }
                String sourcePath = TemplateUtils.getUnzipPath(genTemplateResult.getFileBasePath(), sysNamespaceResult.getName());
                String outPath = GenUtils.build(paramsList, templateCodeInput.getTableName(), sourcePath, genTemplateResult.getLanguage(), sysNamespaceResult, new SysUser(id),
                        sysOsgiPluginService.findFilePathByBoundleType(BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE).getData());
                String targetPath = TemplateUtils.getUnzipPath(outPath + SplitCharEnum.SLASH.getName() + templateCodeInput.getTableName(), sysNamespaceResult.getName());
                String uuid = IdGenUtils.randomUUID(RANDOM_MAX);
                RedisCacheUtils.setSysCache(CACHE_CODE_PREFIX + uuid, TemplateUtils.getDownloadPath(targetPath));
                return new Response<>(uuid);
            }
            throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    @PostMapping(value = { "download/{uuid}" })
    @ApiOperation(value = "下载", notes = "下载")
    public void download(@PathVariable String uuid, HttpServletResponse response) {
        String path = RedisCacheUtils.getSysCache(CACHE_TMPL_PREFIX + uuid);
        if (StringUtils.isNotEmpty(path)) {
            DownloadUtils.downloadFile(response, path);
        }
        return;
    }

    @PostMapping(value = { "download/code/{uuid}" })
    @ApiOperation(value = "下载", notes = "下载")
    public void downloadCode(@PathVariable String uuid, HttpServletResponse response) {
        String path = RedisCacheUtils.getSysCache(CACHE_CODE_PREFIX + uuid);
        if (StringUtils.isNotEmpty(path)) {
            DownloadUtils.downloadFile(response, path);
        }
        return;
    }

    @PostMapping(value = { "push" })
    @ApiOperation(value = "上传", notes = "上传模版文件")
    public Response<GenTemplateResult> upload(@RequestParam("file") MultipartFile file, TemplateInput templateInput) {
        AuthModel authModel = new AuthModel();
        authModel.setUsername(templateInput.getUsername());
        authModel.setUsername(templateInput.getPassword());
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(templateInput.getNamespace());
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(authModel, sysNamespaceResult.getPermission());
            String result = TemplateUtils.upload(file, sysNamespaceResult);
            if (StringUtils.isNotEmpty(result)) {
                String version = templateInput.getVersion();
                Integer language = DEFAULT_LANGUAGE;
                Integer field = null;
                if (StringUtils.isEmpty(version)) {
                    version = DEFAULT_VERSION;
                }
                if (StringUtils.isNotEmpty(templateInput.getLanguage())) {
                    language = LanguageEnum.getCode(templateInput.getLanguage());
                    if (language == null) {
                        throw new BusinessException(BusinessErrorEnum.PLUGIN_LANGUAGE_NOT_EXIST);
                    }
                }
                if (StringUtils.isNotEmpty(templateInput.getField())) {
                    GenFieldQuery genFieldQuery = new GenFieldQuery();
                    genFieldQuery.setName(templateInput.getField());
                    genFieldQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
                    GenFieldPO genFieldPO = new GenFieldPO(genFieldQuery);
                    Response<List<GenFieldResult>> genFieldResultListRes = genFieldService.findList(genFieldPO);
                    if (CollectionUtils.isEmpty(genFieldResultListRes.getData())) {
                        throw new BusinessException(BusinessErrorEnum.FIELD_NOT_EXIST);
                    }
                    field = Integer.valueOf(genFieldResultListRes.getData().get(0).getId());
                }
                GenTemplate genTemplate = new GenTemplate();
                genTemplate.setFileBasePath(result);
                genTemplate.setLanguage(language);
                genTemplate.setDescription(templateInput.getDescription());
                genTemplate.setVersion(version);
                genTemplate.setGenFieldId(field);
                genTemplate.setName(templateInput.getName());
                genTemplate.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
                return genTemplateService.save(genTemplate);
            }
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

}