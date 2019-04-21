package org.jeecf.manager.module.template.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.enums.IfTypeEnum;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.DownloadUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.TemplateUtils;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.manager.module.template.model.po.GenTemplatePO;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.result.GenTemplateResult;
import org.jeecf.manager.module.template.model.schema.GenTemplateSchema;
import org.jeecf.manager.module.template.service.GenTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 全量模版配置
 * 
 * @author jianyiming
 * @version 2.0
 */
@Controller
@RequestMapping(value = { "template/genTemplateAll" })
@Api(value = "genTemplateAll api", tags = { "全量模版接口" })
public class GenTemplateAllController implements BaseController {

    @Autowired
    private GenTemplateService genTemplateService;

    @Autowired
    private SysNamespaceService sysNamespaceService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.genTemplateAll.view}")
    @ApiOperation(value = "视图", notes = "查看模版配置视图")
    @Override
    public String index(ModelMap map) {
        return "module/template/genTemplateAll";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.genTemplateAll.view}")
    @ApiOperation(value = "列表", notes = "查询全量模版列表")
    public Response<List<GenTemplateResult>> list(@RequestBody Request<GenTemplateQuery, GenTemplateSchema> request) {
        Response<List<GenTemplateResult>> response = genTemplateService.findPage(new GenTemplatePO(request));
        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
            List<Integer> ids = new ArrayList<>();
            GenTemplateQuery genTemplateQuery = new GenTemplateQuery();
            genTemplateQuery.setSysNamespaceId(NamespaceUtils.getNamespaceId());
            Response<List<GenTemplateResult>> templateResultRes = genTemplateService.findList(new GenTemplatePO(genTemplateQuery));
            List<GenTemplateResult> templateResultList = templateResultRes.getData();
            genTemplateService.buildCreateBy(response.getData());
            response.getData().forEach(result -> {
                result.toConvert();
                ids.add(result.getSysNamespaceId());
                if (CollectionUtils.isNotEmpty(templateResultList)) {
                    for (GenTemplateResult genTemplateResult : templateResultList) {
                        if (result.getName().equals(genTemplateResult.getName())) {
                            result.setIsExit(IfTypeEnum.YES.getCode());
                            break;
                        }
                    }
                }
            });

            Response<List<SysNamespaceResult>> namespaceResultRes = sysNamespaceService.findListByIds(ids);
            if (CollectionUtils.isNotEmpty(namespaceResultRes.getData())) {
                List<SysNamespaceResult> namespaceResultList = namespaceResultRes.getData();
                response.getData().forEach(result -> {
                    for (SysNamespaceResult sysNamespaceResult : namespaceResultList) {
                        if (sysNamespaceResult.getId().equals(String.valueOf(result.getSysNamespaceId()))) {
                            result.setName(sysNamespaceResult.getName() + SplitCharEnum.SLASH.getName() + result.getName());
                        }
                    }
                });
            }
        }
        return response;
    }

    @PostMapping(value = { "add/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.genTemplateAll.view}")
    @ApiOperation(value = "添加", notes = "添加模版")
    public Response<GenTemplateResult> add(@PathVariable Integer id) {
        Response<GenTemplateResult> checkGenTemplateRes = genTemplateService.getByAuth(new GenTemplate(String.valueOf(id)));
        if (checkGenTemplateRes.isSuccess() && checkGenTemplateRes.getData() == null) {
            Response<GenTemplateResult> genTemplateRes = genTemplateService.get(new GenTemplate(String.valueOf(id)));
            if (genTemplateRes.getData() != null) {
                GenTemplateResult genTemplateResult = genTemplateRes.getData();
                GenTemplate genTemplate = new GenTemplate();
                BeanUtils.copyProperties(genTemplateResult, genTemplate);
                genTemplate.setId(null);
                return genTemplateService.saveByAuth(genTemplate);
            }
            throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
    }

    @PostMapping(value = { "download/template/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.genTemplate.view}")
    @ApiOperation(value = "模版文件下载", notes = "下载")
    public void templateDownload(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        GenTemplate genTemplate = genTemplateService.get(new GenTemplate(id)).getData();
        if (genTemplate != null) {
            SysNamespace sysNamespace = sysNamespaceService.get(new SysNamespace(String.valueOf(genTemplate.getSysNamespaceId()))).getData();
            if (sysNamespace != null) {
                String zipFilePath = TemplateUtils.getZipFilePath(genTemplate.getFileBasePath(), sysNamespace.getName());
                DownloadUtils.downloadFile(response, zipFilePath);
            }
        }
        return;
    }

}
