package org.jeecf.manager.module.extend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.utils.DownloadUtils;
import org.jeecf.manager.common.utils.PluginUtils;
import org.jeecf.manager.module.extend.model.domain.SysOsgiPlugin;
import org.jeecf.manager.module.extend.model.po.SysOsgiPluginPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.result.SysOsgiPluginResult;
import org.jeecf.manager.module.extend.model.schema.SysOsgiPluginSchema;
import org.jeecf.manager.module.extend.service.SysOsgiPluginService;
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
 * OSGI 插件controller
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "extend/sysOsgiPlugin" })
@Api(value = "sysOsgiPlugin api", tags = { "OSGI插件接口" })
public class SysOsgiPluginController implements BaseController {

    @Autowired
    private SysOsgiPluginService sysOsgiPluginService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysOsgiPlugin.view}")
    @ApiOperation(value = "视图", notes = "查看OSGI插件参数视图")
    @Override
    public String index(ModelMap map) {
        return "module/extend/sysOsgiPlugin";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPlugin.view}")
    @ApiOperation(value = "列表", notes = "查询OSGI插件数据")
    public Response<List<SysOsgiPluginResult>> list(@RequestBody Request<SysOsgiPluginQuery, SysOsgiPluginSchema> request) {
        Response<List<SysOsgiPluginResult>> response = sysOsgiPluginService.findPageByAuth(new SysOsgiPluginPO(request));
        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
            sysOsgiPluginService.buildCreateBy(response.getData());
            response.getData().forEach(result -> {
                result.toCovert();
            });
        }
        return response;
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPlugin.edit}")
    @ApiOperation(value = "删除", notes = "删除OSGI插件数据")
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysOsgiPluginService.deleteByAuth(new SysOsgiPlugin(id));
    }

    @PostMapping(value = { "download/plugin/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysOsgiPlugin.view}")
    @ApiOperation(value = "插件文件下载", notes = "下载")
    public void templateDownload(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        SysOsgiPlugin sysOsgiPlugin = sysOsgiPluginService.get(new SysOsgiPlugin(id)).getData();
        DownloadUtils.downloadFile(response, PluginUtils.getFilePath(sysOsgiPlugin.getName()));
        return;
    }

}
