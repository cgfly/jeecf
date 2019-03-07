package org.jeecf.manager.module.extend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.jeecf.common.enums.DelFlagEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.DownloadUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.PluginUtils;
import org.jeecf.manager.module.extend.model.domain.SysOsgiPlugin;
import org.jeecf.manager.module.extend.model.po.SysOsgiPluginPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.result.SysOsgiPluginResult;
import org.jeecf.manager.module.extend.model.schema.SysOsgiPluginSchema;
import org.jeecf.manager.module.extend.service.SysOsgiPluginService;
import org.jeecf.manager.validate.groups.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 全量OSGI插件接口
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "extend/sysOsgiPluginAll" })
@Api(value = "sysOsgiPluginAll api", tags = { "全量OSGI插件接口" })
public class SysOsgiPluginAllController implements CurdController<SysOsgiPluginQuery, SysOsgiPluginResult, SysOsgiPluginSchema, SysOsgiPlugin> {

    @Autowired
    private SysOsgiPluginService sysOsgiPluginService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysOsgiPluginAll.view}")
    @ApiOperation(value = "视图", notes = "查看全量OSGI插件视图")
    @Override
    public String index(ModelMap map) {
        return "module/extend/sysOsgiPluginAll";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.view}")
    @ApiOperation(value = "列表", notes = "查询OSGI插件数据")
    @Override
    public Response<List<SysOsgiPluginResult>> list(@RequestBody Request<SysOsgiPluginQuery, SysOsgiPluginSchema> request) {
        request.getData().setSysNamespaceId(0);
        Response<List<SysOsgiPluginResult>> response = sysOsgiPluginService.findPage(new SysOsgiPluginPO(request));
        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
            sysOsgiPluginService.buildCreateBy(response.getData());
            response.getData().forEach(result -> {
                result.toCovert();
            });
        }
        return response;
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "更新", notes = "更新OSGI插件数据")
    @Override
    public Response<SysOsgiPluginResult> save(@RequestBody @Validated({ Add.class }) SysOsgiPlugin sysOsgiPlugin) {
        if (sysOsgiPlugin.isNewRecord()) {
            SysOsgiPluginQuery query = new SysOsgiPluginQuery();
            query.setName(sysOsgiPlugin.getName());
            List<SysOsgiPluginResult> sysOsgiPluginList = sysOsgiPluginService.findList(new SysOsgiPluginPO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysOsgiPluginList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
            return sysOsgiPluginService.insert(sysOsgiPlugin);
        }
        sysOsgiPlugin.setSysNamespaceId(null);
        sysOsgiPluginService.updateByName(sysOsgiPlugin);
        return sysOsgiPluginService.get(sysOsgiPlugin);
    }

    @PostMapping(value = { "upload" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "上传", notes = "上传模版文件")
    public Response<String> upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        PluginUtils.upload(file);
        return new Response<>(fileName);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "删除", notes = "删除OSGI插件数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysOsgiPluginService.delete(new SysOsgiPlugin(id));
    }

    @PostMapping(value = { "download/plugin/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.view}")
    @ApiOperation(value = "插件文件下载", notes = "下载")
    public void templateDownload(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        SysOsgiPlugin sysOsgiPlugin = sysOsgiPluginService.get(new SysOsgiPlugin(id)).getData();
        DownloadUtils.downloadFile(response, PluginUtils.getFilePath(sysOsgiPlugin.getName()));
        return;
    }

    @PostMapping(value = { "gain/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "获取", notes = "获取OSGI插件数据")
    public Response<SysOsgiPluginResult> gain(@PathVariable("id") String id) {
        SysOsgiPluginResult sysOsgiPluginResult = sysOsgiPluginService.get(new SysOsgiPlugin(id)).getData();
        if (sysOsgiPluginResult != null) {
            SysOsgiPluginQuery query = new SysOsgiPluginQuery();
            query.setName(sysOsgiPluginResult.getName());
            query.setSysNamespaceId(NamespaceUtils.getNamespaceId());
            List<SysOsgiPluginResult> sysOsgiPlugins = sysOsgiPluginService.findList(new SysOsgiPluginPO(query)).getData();
            if (CollectionUtils.isEmpty(sysOsgiPlugins)) {
                sysOsgiPluginResult.setNewRecord(true);
                return sysOsgiPluginService.saveByAuth(sysOsgiPluginResult);
            }
            throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "active/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "激活", notes = "激活OSGI插件数据")
    public Response<Integer> active(@PathVariable("id") String id) {
        SysOsgiPluginResult sysOsgiPluginResult = sysOsgiPluginService.get(new SysOsgiPlugin(id)).getData();
        if (sysOsgiPluginResult != null) {
            SysOsgiPlugin sysOsgiPlugin = new SysOsgiPlugin();
            sysOsgiPlugin.setName(sysOsgiPluginResult.getName());
            sysOsgiPlugin.setDelFlag(DelFlagEnum.NO.getCode());
            return sysOsgiPluginService.updateByName(sysOsgiPlugin);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "invalid/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPluginAll.edit}")
    @ApiOperation(value = "失效", notes = "失效OSGI插件数据")
    public Response<Integer> effect(@NotEmpty @PathVariable("id") String id) {
        SysOsgiPluginResult sysOsgiPluginResult = sysOsgiPluginService.get(new SysOsgiPlugin(id)).getData();
        if (sysOsgiPluginResult != null) {
            SysOsgiPlugin sysOsgiPlugin = new SysOsgiPlugin();
            sysOsgiPlugin.setName(sysOsgiPluginResult.getName());
            sysOsgiPlugin.setDelFlag(DelFlagEnum.YES.getCode());
            return sysOsgiPluginService.updateByName(sysOsgiPlugin);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

}
