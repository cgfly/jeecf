package org.jeecf.manager.module.main.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.main.model.LoginVo;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.subject.UserSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 登录接口 controller
 * @author jianyiming
 *
 */
@Controller
@Api(value = "login api", tags = { "登录接口" })
public class LoginController {

	@Autowired
	private UserSubject userSubject;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value = "视图", notes = "登录视图")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "验证", notes = "登录验证")
	public Response<Integer> login(@RequestBody LoginVo loginVo) {
		boolean rememberMe = loginVo.isRememberMe();
		try {
			SecurityUtils.getSubject()
					.login(new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), rememberMe));
			SysUser sysUser = new SysUser();
			sysUser.setUsername(loginVo.getUsername());
			userSubject.updateLogin(sysUser);
		} catch (AuthenticationException e) {
			throw new BusinessException(BusinessErrorEnum.USER_USER_AND_PASSWORD_ERROR);
		}
		return new Response<Integer>(1);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ApiOperation(value = "退出", notes = "退出登录")
	public String logout(ModelMap map) {
		SysUser sysUser = UserUtils.getCurrentUser();
		SecurityUtils.getSubject().logout();
		userSubject.updateLogout(sysUser);
		return "login";
	}

}
