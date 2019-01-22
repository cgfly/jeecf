package org.jeecf.manager.module.doc.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 功能结束 controller
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "doc/functionIntroduction" })
@Api(value = "functionIntroduction api", tags = { "功能介绍接口" })
public class FunctionIntroduction {

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("doc:functionIntroduction:view")
    @ApiOperation(value = "视图", notes = "查看api视图")
    public String index(ModelMap map) {
        return "module/doc/functionIntroduction";
    }

}
