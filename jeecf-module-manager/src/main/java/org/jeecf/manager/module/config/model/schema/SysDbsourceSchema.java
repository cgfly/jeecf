package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 数据源schema
 * @author jianyiming
 *
 */
public class SysDbsourceSchema {
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", name = "id")
	private boolean id = true;
	/**
	 * 关键字
	 */
	@ApiModelProperty(value = "关键字", name = "keyName")
	private boolean keyName = true;
	/**
	 * 连接串
	 */
	@ApiModelProperty(value = "连接串", name = "url")
	private boolean url = true;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", name = "userName")
	private boolean userName = true;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", name = "password")
	private boolean password = true;
	/**
	 * 权限
	 */
	@ApiModelProperty(value="权限",name="permission")
	private boolean permission = true;
	/**
	 * 连接是否可用
	 */
	@ApiModelProperty(value = "连接是否可用", name = "usable")
	private boolean usable = true;
	
	/**
	 * 逻辑删除
	 */
	@ApiModelProperty(value = "逻辑删除", name = "delFlag")
	private boolean delFlag = true;
	
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间", name = "updateDate")
	private boolean updateDate = true;

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isKeyName() {
		return keyName;
	}

	public void setKeyName(boolean keyName) {
		this.keyName = keyName;
	}

	public boolean isUrl() {
		return url;
	}

	public void setUrl(boolean url) {
		this.url = url;
	}

	public boolean isUserName() {
		return userName;
	}

	public void setUserName(boolean userName) {
		this.userName = userName;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	public boolean isUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isPassword() {
		return password;
	}

	public void setPassword(boolean password) {
		this.password = password;
	}

	public boolean getPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
}
