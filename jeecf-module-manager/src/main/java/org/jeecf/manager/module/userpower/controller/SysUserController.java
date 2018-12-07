package org.jeecf.manager.module.userpower.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.po.SysRolePO;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.po.SysUserRolePO;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.query.SysUserRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysRoleResult;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.manager.module.userpower.model.result.SysUserRoleResult;
import org.jeecf.manager.module.userpower.model.schema.SysUserSchema;
import org.jeecf.manager.module.userpower.service.SysRoleService;
import org.jeecf.manager.module.userpower.service.SysUserRoleService;
import org.jeecf.manager.module.userpower.service.SysUserService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统用户
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "userpower/sysUser" })
@Api(value="sysUser api",tags={"系统用户接口"})
public class SysUserController extends BaseController<SysUserQuery,SysUserResult,SysUserSchema,SysUser> {

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SecurityFacade securityFacade;

	@GetMapping(value = { "", "index" })
	@RequiresPermissions("userpower:sysUser:view")
	@ApiOperation(value = "视图", notes = "查看系统用户视图")
	@Override
	public String index(ModelMap map) {
		return "module/userpower/sysUser";
	}

	@PostMapping(value = { "list" })
	@ResponseBody
	@RequiresPermissions("userpower:sysUser:view")
	@ApiOperation(value = "列表", notes = "查询系统用户列表")
	@Override
	public Response<List<SysUserResult>> list(@RequestBody Request<SysUserQuery,SysUserSchema> request) {
		SysUserSchema schema =	request.getSchema();
		if(schema == null) {
			schema = new SysUserSchema();
			request.setSchema(schema);
		}
		schema.setPassword(false);
		return securityFacade.findUser(new SysUserPO(request));
	}

	@PostMapping(value = { "roles/{userId}" })
	@ResponseBody
	@RequiresPermissions("userpower:sysUser:view")
	@ApiOperation(value = "列表", notes = "查询系统角色列表")
	public Response<List<SysRoleResult>> roles(@PathVariable("userId") String userId) {
		Response<List<SysRoleResult>> sysRoleRes = sysRoleService.findList(new SysRolePO(new SysRoleQuery()));
		if (StringUtils.isNotEmpty(userId)) {
			SysUserRoleQuery queryUserRole = new SysUserRoleQuery();
			queryUserRole.setSysUser(new SysUser(userId));
			Response<List<SysUserRoleResult>> userRoleRes = sysUserRoleService.findList(new SysUserRolePO(queryUserRole));
			List<SysUserRoleResult> userRoleList = userRoleRes.getData();
			sysRoleRes.getData().forEach(sysRole -> {
				userRoleList.forEach(userRole -> {
					if (sysRole.getId().equals(userRole.getSysRole().getId())) {
						sysRole.setChecked(true);
					}
				});
			});
		}
		return sysRoleRes;
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("userpower:sysUser:edit")
	@ApiOperation(value = "更新", notes = "更新系统用户数据")
	@Override
	public Response<Integer> save(@RequestBody @Validated({Add.class}) SysUser sysUser) {
		if(sysUser.isNewRecord()) {
			if(StringUtils.isBlank(sysUser.getPassword())) {
				throw new BusinessException(BusinessErrorEnum.USER_PASSWORD_ERROR);
			}
			SysUserQuery query = new SysUserQuery();
			query.setUsername(sysUser.getUsername());
			List<SysUserResult> sysUserList = sysUserService.findList(new SysUserPO(query)).getData();
			if(CollectionUtils.isNotEmpty(sysUserList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		return securityFacade.saveUser(sysUser);
	}

	@PostMapping(value = { "delete/{id}" })
	@ResponseBody
	@RequiresPermissions("userpower:sysUser:edit")
	@ApiOperation(value = "删除", notes = "删除系统用户数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		return securityFacade.deleteUser(new SysUser(id));
	}

}