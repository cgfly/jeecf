package org.jeecf.manager.module.userpower.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户schema
 * @author jianyiming
 *
 */
public class SysUserSchema {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",name="id")
	private boolean id = true;
	/**
	 * 账户
	 */
	@ApiModelProperty(value="账户",name="username")
	private boolean username = true;
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码",name="password")
	private boolean password = true;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty(value="用户名",name="name")
	private boolean name = true;
	
	/**
	 * 组织结构
	 */
	@ApiModelProperty(value="组织结构",name="sysOffice")
	private boolean sysOffice = true;
	
	/**
	 * 角色集
	 */
	@ApiModelProperty(value="角色集",name="roleNames")
	private boolean roleNames = true;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",name="updateDate")
	private boolean createDate = true;
	public boolean isId() {
		return id;
	}
	public void setId(boolean id) {
		this.id = id;
	}
	public boolean isUsername() {
		return username;
	}
	public void setUsername(boolean username) {
		this.username = username;
	}
	public boolean isName() {
		return name;
	}
	public void setName(boolean name) {
		this.name = name;
	}
	public boolean isRoleNames() {
		return roleNames;
	}
	public void setRoleNames(boolean roleNames) {
		this.roleNames = roleNames;
	}
	public boolean isCreateDate() {
		return createDate;
	}
	public void setCreateDate(boolean createDate) {
		this.createDate = createDate;
	}
	public boolean isPassword() {
		return password;
	}
	public void setPassword(boolean password) {
		this.password = password;
	}
	public boolean isSysOffice() {
		return sysOffice;
	}
	public void setSysOffice(boolean sysOffice) {
		this.sysOffice = sysOffice;
	}
	
}
