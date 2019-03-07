package org.jeecf.manager.module.userpower.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysPwd;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.manager.validate.groups.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统用户密码修改
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "userpower/sysPwd" })
@Api(value = "sysPwd api", tags = { "密码修改接口" })
public class SysPwdController implements BaseController {

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysPwd.view}")
    @ApiOperation(value = "视图", notes = "查看系统用户密码视图")
    @Override
    public String index(ModelMap map) {
        return "module/userpower/sysPwd";
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysPwd.edit}")
    @ApiOperation(value = "更新", notes = "更新系统用户密码数据")
    public Response<SysUserResult> save(@RequestBody @Validated({ Add.class }) SysPwd sysPwd) {
        return securityFacade.updatePassword(sysPwd);
    }

}
