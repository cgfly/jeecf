package org.jeecf.manager.module.userpower.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.common.utils.ResponseUtils;
import org.jeecf.common.utils.SysEntrypt;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.domain.SysPwd;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.domain.SysRolePower;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.domain.SysUserRole;
import org.jeecf.manager.module.userpower.model.po.SysRolePowerPO;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.po.SysUserRolePO;
import org.jeecf.manager.module.userpower.model.query.SysRolePowerQuery;
import org.jeecf.manager.module.userpower.model.query.SysUserRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;
import org.jeecf.manager.module.userpower.model.result.SysRolePowerResult;
import org.jeecf.manager.module.userpower.model.result.SysRoleResult;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.manager.module.userpower.model.result.SysUserRoleResult;
import org.jeecf.manager.module.userpower.service.SysPowerService;
import org.jeecf.manager.module.userpower.service.SysRolePowerService;
import org.jeecf.manager.module.userpower.service.SysRoleService;
import org.jeecf.manager.module.userpower.service.SysUserRoleService;
import org.jeecf.manager.module.userpower.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 安全验证 门面
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class SecurityFacade {

	@Autowired
	private SysRolePowerService sysRolePowerService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysPowerService sysPowerService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	public Response<List<SysUserResult>> findUser(SysUserPO sysUserPO) {
		Response<List<SysUserResult>> userRes = sysUserService.findPageByAuth(sysUserPO);
		if (CollectionUtils.isNotEmpty(userRes.getData())) {
			userRes.getData().forEach(sysUser -> {
				Response<String> roleNamesRes = this.findRoleNames(sysUser.getId());
				if (StringUtils.isNotEmpty(roleNamesRes.getData())) {
					sysUser.setRoleNames(roleNamesRes.getData());
				}
			});
		}
		return userRes;

	}

	public Response<List<SysRole>> findRole(String userId) {
		List<SysRole> sysRoleList = new ArrayList<SysRole>();
		SysUserRoleQuery queryUserRole = new SysUserRoleQuery();
		queryUserRole.setSysUser(new SysUser(userId));
		Response<List<SysUserRoleResult>> userRoleRes = sysUserRoleService.findList(new SysUserRolePO(queryUserRole));
		if (userRoleRes.isSuccess() && CollectionUtils.isNotEmpty(userRoleRes.getData())) {
			userRoleRes.getData().forEach(userRole -> {
				sysRoleList.add(userRole.getSysRole());
			});
		}
		return new Response<List<SysRole>>(sysRoleList);
	}

	public Response<String> findRoleNames(String userId) {
		SysUserRoleQuery queryUserRole = new SysUserRoleQuery();
		queryUserRole.setSysUser(new SysUser(userId));
		Response<List<SysUserRoleResult>> userRoleRes = sysUserRoleService.findList(new SysUserRolePO(queryUserRole));
		StringBuffer roleNamesBuf = new StringBuffer("");
		if (userRoleRes.isSuccess() && CollectionUtils.isNotEmpty(userRoleRes.getData())) {
			userRoleRes.getData().forEach(userRole -> {
				roleNamesBuf.append(userRole.getSysRole().getName() + ",");
			});
		}
		String roleNames = roleNamesBuf.toString();
		if (roleNames.length() > 1) {
			roleNames = StringUtils.substringBeforeLast(roleNames, ",");
		}
		return new Response<String>(roleNames);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<SysRoleResult> saveRole(SysRole sysRole) {
		Response<SysRoleResult> sysRoleRes = sysRoleService.save(sysRole);
		List<String> sysPowerIds = sysRole.getSysPowerIds();
		if (CollectionUtils.isNotEmpty(sysPowerIds)) {
			if (StringUtils.isNotEmpty(sysRole.getId())) {
				SysRolePower deleteRolePower = new SysRolePower();
				deleteRolePower.setSysRole(sysRole);
				sysRolePowerService.delete(deleteRolePower);
			}
			SysPower sysPower = new SysPower();
			sysPowerIds.forEach(powerId -> {
				SysRolePower sysRolePower = new SysRolePower();
				sysRolePower.setSysRole(sysRole);
				sysPower.setId(powerId);
				sysRolePower.setSysPower(sysPower);
				sysRolePowerService.save(sysRolePower);
			});
		}
		return sysRoleRes;
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> deleteRole(String sysRoleId) {
		SysRole sysRole = new SysRole(sysRoleId);
		sysRoleService.delete(sysRole);
		SysRolePower deleteRolePower = new SysRolePower();
		deleteRolePower.setSysRole(sysRole);
		sysRolePowerService.delete(deleteRolePower);
		SysUserRole deleteUserRole = new SysUserRole();
		deleteUserRole.setSysRole(sysRole);
		sysUserRoleService.delete(deleteUserRole);
		return new Response<Integer>(true, 1);
	}

	public Response<List<SysPower>> findPower(String sysRoleId) {
		List<SysPower> sysPowerList = new ArrayList<SysPower>();
		SysRolePowerQuery queryRolePower = new SysRolePowerQuery();
		queryRolePower.setSysRole(new SysRole(sysRoleId));
		Response<List<SysRolePowerResult>> sysRolePowerRes = sysRolePowerService
				.findList(new SysRolePowerPO(queryRolePower));
		if (ResponseUtils.isNotEmpty(sysRolePowerRes)) {
			sysRolePowerRes.getData().forEach(sysRolePower -> {
				sysPowerList.add(sysRolePower.getSysPower());
			});
		}
		return new Response<List<SysPower>>(sysPowerList);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> deletePower(String powerId) {
		SysPowerResult sysPowerResult = sysPowerService.get(new SysPower(powerId)).getData();
		if (sysPowerResult != null) {
			Response<List<SysPowerResult>> sysPowerRes = sysPowerService.findChilds(powerId);
			List<SysPowerResult> sysPowerList = sysPowerRes.getData();
			sysPowerList.add(sysPowerResult);
			if (CollectionUtils.isNotEmpty(sysPowerList)) {
				sysPowerList.forEach(delPower -> {
					SysRolePower delRolePower = new SysRolePower();
					delRolePower.setSysPower(delPower);
					sysRolePowerService.delete(delRolePower);
					sysPowerService.delete(delPower);
				});
			}
		}
		return new Response<Integer>(true, 1);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<SysUserResult> saveUser(SysUser sysUser) {
		if (StringUtils.isEmpty(sysUser.getId())) {
			sysUser.setNewRecord(true);
			sysUser.setId(IdGenUtils.uuid());
			sysUser.setPassword(SysEntrypt.entryptPassword(sysUser.getPassword()));
		}
		Response<SysUserResult> sysUserRes = sysUserService.save(sysUser);
		List<String> sysRoleids = sysUser.getSysRoleIds();
		SysRole sysRole = new SysRole();
		if (CollectionUtils.isNotEmpty(sysRoleids)) {
			if (StringUtils.isNotEmpty(sysUser.getId())) {
				SysUserRole deleteUserRole = new SysUserRole();
				deleteUserRole.setSysUser(sysUser);
				sysUserRoleService.delete(deleteUserRole);
			}
			sysRoleids.forEach(roleId -> {
				SysUserRole sysUserRole = new SysUserRole();
				sysRole.setId(roleId);
				sysUserRole.setSysRole(sysRole);
				sysUserRole.setSysUser(sysUser);
				sysUserRoleService.save(sysUserRole);
			});
		}
		return sysUserRes;
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<SysUserResult> updatePassword(SysPwd sysPwd) {
		SysUser sysUser = UserUtils.getCurrentUser();
		if (sysUser != null) {
			boolean isLogin = SysEntrypt.validatePassword(sysPwd.getPassword(), sysUser.getPassword());
			if (isLogin) {
				sysUser.setPassword(SysEntrypt.entryptPassword(sysPwd.getNewPassword()));
				return sysUserService.save(sysUser);
			} else {
				throw new BusinessException(BusinessErrorEnum.USER_PASSWORD_ERROR);
			}
		} else {
			throw new BusinessException(BusinessErrorEnum.USER_NOT);

		}
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> deleteUser(String userId) {
		SysUser sysUser = new SysUser(userId);
		sysUserService.deleteByFlag(sysUser);
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setSysUser(sysUser);
		sysUserRoleService.delete(sysUserRole);
		return new Response<Integer>(true, 1);
	}

	public Response<Set<String>> findPermission(String userId) {
		Response<List<SysRole>> sysRoleRes = this.findRole(userId);
		Set<String> roleSet = new HashSet<>();
		Set<String> powerSet = new HashSet<String>();
		if (ResponseUtils.isNotEmpty(sysRoleRes)) {
			sysRoleRes.getData().forEach(sysRole -> {
				roleSet.add(sysRole.getEnname());
				Response<List<SysPower>> sysPowerRes = this.findPower(sysRole.getId());
				if (ResponseUtils.isNotEmpty(sysPowerRes)) {
					sysPowerRes.getData().forEach(sysPower -> {
						String afterLast = StringUtils.substringAfterLast(sysPower.getPermission(), ":");
						if (PermissionUtils.MATCH_PERMISSION.equals(afterLast)) {
							String beforeLast = StringUtils.substringBeforeLast(sysPower.getPermission(), ":");
							for (String value : PermissionUtils.RESOLVE_PERMISSION) {
								powerSet.add(beforeLast + ":" + value);
							}
						} else {
							powerSet.add(sysPower.getPermission());
						}
					});
				}
			});
		}
		return new Response<Set<String>>(powerSet);
	}
	
}
