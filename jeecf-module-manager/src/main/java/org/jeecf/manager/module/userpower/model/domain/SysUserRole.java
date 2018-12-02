package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;

import org.jeecf.manager.common.model.BaseEntity;

/**
 * 用户角色对照
 * 
 * @author GloryJian
 * @version 1.0
 */
public class SysUserRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 关联用户
	 */
	private SysUser sysUser;
	/**
	 * 关联角色
	 */
	private SysRole sysRole;

	public SysUserRole() {
		super();
	}

	public SysUserRole(String id) {
		super(id);
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}