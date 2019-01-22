package org.jeecf.manager.module.operation.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据库监控
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "operation/druid" })
@Api(value = "druid api", tags = { "数据库连接池视图" })
public class DruidController {

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("operation:druid:view")
    @ApiOperation(value = "视图", notes = "查看数据库连接池视图")
    public String index(ModelMap map) {
        return "module/operation/druid";
    }
}
