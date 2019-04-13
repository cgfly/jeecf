package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.extend.model.po.SysOsgiPluginPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.result.SysOsgiPluginResult;
import org.jeecf.manager.module.extend.service.SysOsgiPluginService;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.osgi.enums.LanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 命令行插件属性接口 controller
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/plugin" })
@Api(value = "命令行插件属性 api", tags = { "命令行插件属性接口" })
public class PluginController {

    @Autowired
    private SysOsgiPluginService sysOsgiPluginService;

    @Autowired
    SysNamespaceService sysNamespaceService;

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = { "languages" })
    @ApiOperation(value = "列表", notes = "返回插件语言列表")
    public Response<List<String>> languages(@RequestBody AuthModel authModel) {
        userAuthService.auth(authModel);
        List<String> languages = new ArrayList<>();
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            languages.add(languageEnum.getName());
        }
        return new Response<>(languages);
    }

    @PostMapping(value = { "plugins/{namespace}" })
    @ApiOperation(value = "列表", notes = "返回用户插件列表")
    public Response<List<String>> plugins(@RequestBody AuthModel authModel, @PathVariable(required = false) String namespace) {
        Response<SysUserResult> sysUserResultRes = userAuthService.auth(authModel);
        SysNamespace sysNamespace = sysNamespaceService.get(sysUserResultRes.getData().getId(), namespace);
        if (sysNamespace != null) {
            List<String> pluginList = new ArrayList<>();
            userAuthService.authPermission(sysUserResultRes.getData().getId(), sysNamespace.getPermission());
            SysOsgiPluginQuery sysOsgiPluginQuery = new SysOsgiPluginQuery();
            sysOsgiPluginQuery.setSysNamespaceId(Integer.valueOf(sysNamespace.getId()));
            SysOsgiPluginPO sysOsgiPluginPO = new SysOsgiPluginPO(sysOsgiPluginQuery);
            Response<List<SysOsgiPluginResult>> sysOsgiPluginResultListRes = sysOsgiPluginService.findList(sysOsgiPluginPO);
            if (CollectionUtils.isNotEmpty(sysOsgiPluginResultListRes.getData())) {
                sysOsgiPluginResultListRes.getData().forEach(sysOsgiPluginResult -> {
                    pluginList.add(sysOsgiPluginResult.getName());
                });
            }
            return new Response<>(pluginList);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    @PostMapping(value = { "detail/{namespace}/{name}", "detail/{name}" })
    @ApiOperation(value = "详情", notes = "返回用户插件详情")
    public Response<SysOsgiPluginResult> detail(@RequestBody AuthModel authModel, @PathVariable(required = false) String namespace, @PathVariable String name) {
        Response<SysUserResult> sysUserResultRes = userAuthService.auth(authModel);
        SysNamespace sysNamespace = sysNamespaceService.get(sysUserResultRes.getData().getId(), namespace);
        if (sysNamespace != null) {
            userAuthService.authPermission(sysUserResultRes.getData().getId(), sysNamespace.getPermission());
            SysOsgiPluginQuery sysOsgiPluginQuery = new SysOsgiPluginQuery();
            sysOsgiPluginQuery.setSysNamespaceId(Integer.valueOf(sysNamespace.getId()));
            sysOsgiPluginQuery.setName(name);
            SysOsgiPluginPO sysOsgiPluginPO = new SysOsgiPluginPO(sysOsgiPluginQuery);
            Response<List<SysOsgiPluginResult>> sysOsgiPluginResultListRes = sysOsgiPluginService.findList(sysOsgiPluginPO);
            if (CollectionUtils.isNotEmpty(sysOsgiPluginResultListRes.getData())) {
                return new Response<>(sysOsgiPluginResultListRes.getData().get(0));
            }
            return new Response<>(null);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

}
