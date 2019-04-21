package org.jeecf.manager.module.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.jeecf.common.model.Response;
import org.jeecf.engine.mysql.enums.TableTypeEnum;
import org.jeecf.manager.common.enums.PermissionLabelEnum;
import org.jeecf.manager.common.enums.TreeEventEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.osgi.enums.BoundleEnum;
import org.jeecf.osgi.enums.LanguageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 通用接口
 * 
 * @author jianyiming
 * @version 2.0
 */
@Controller
@RequestMapping(value = { "common" })
@Api(value = "common api", tags = { "系统通用接口" })
public class CommonController {

    @PostMapping(value = { "enums" })
    @ResponseBody
    @ApiOperation(value = "枚举", notes = "查询枚举列表")
    public Response<Map<String, String>> enums() {
        Map<String, String> result = new HashMap<>(12);
        result.put("treeEventEnum", TreeEventEnum.toJsonString());
        result.put("tableTypeEnum", TableTypeEnum.toJsonString());
        result.put("osgiBoundleTypeEnum", BoundleEnum.toJsonString());
        result.put("languageEnum", LanguageEnum.toJsonString());
        result.put("permissionLabelEnum", PermissionLabelEnum.toJsonString());
        return new Response<>(result);
    }

    @PostMapping(value = { "namespace" })
    @ResponseBody
    @ApiOperation(value = "命名空间", notes = "当前命名空间")
    public Response<String> namespace() {
        SysNamespace sysNamespace = NamespaceUtils.getNamespace(UserUtils.getCurrentUserId());
        return new Response<>(sysNamespace.getName());
    }

}
