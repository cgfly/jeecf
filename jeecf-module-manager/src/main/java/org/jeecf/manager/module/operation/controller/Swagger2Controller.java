package org.jeecf.manager.module.operation.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * api 查看测试
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "operation/swagger2" })
@Api(value = "swagger2 api", tags = { "api视图" })
public class Swagger2Controller {

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.swagger2.view}")
    @ApiOperation(value = "视图", notes = "查看api视图")
    public String index(ModelMap map) {
        return "module/operation/swagger2";
    }
}
