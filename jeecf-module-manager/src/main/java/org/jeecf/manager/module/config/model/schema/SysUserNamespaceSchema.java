package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 系统用户命名空间对应 schema
 * @author jianyiming
 *
 */
public class SysUserNamespaceSchema {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", name = "id")
	private boolean id = true;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id", name = "userId")
	private boolean userId = true;
	/**
	 * 命名空间id
	 */
	@ApiModelProperty(value = "命名空间id", name = "namespaceId")
	private boolean namespaceId = true;
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
	public boolean isUserId() {
		return userId;
	}
	public void setUserId(boolean userId) {
		this.userId = userId;
	}
	public boolean isNamespaceId() {
		return namespaceId;
	}
	public void setNamespaceId(boolean namespaceId) {
		this.namespaceId = namespaceId;
	}
	
}
