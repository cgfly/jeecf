package org.jeecf.manager.module.cli.controller;

import org.jeecf.common.model.Response;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 命令行登录接口
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/user" })
@Api(value = "命令行用户操作 api", tags = { "命令行用户接口" })
public class UserController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping(value = { "login" })
    @ApiOperation(value = "登录", notes = "登录信息验证")
    public Response<SysUserResult> list(@RequestBody AuthModel authModel) {
        return userAuthService.auth(authModel);
    }

}
