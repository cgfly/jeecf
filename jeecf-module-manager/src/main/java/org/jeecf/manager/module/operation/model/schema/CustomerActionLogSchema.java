package org.jeecf.manager.module.operation.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户操作日志 schema
 * @author jianyiming
 *
 */
public class CustomerActionLogSchema {
	
	/**
	* 主机
	*/
	@ApiModelProperty(value = "主机", name = "host")
	private boolean ip = true;
	/**
	 * 用户姓名
	 */
	@ApiModelProperty(value = "用户姓名", name = "userName")
	private boolean userName = true;
	/**
	 * 操作类型
	 */
	@ApiModelProperty(value = "操作类型", name = "actionType")
	private boolean actionType = true;
	/**
	 * 操作类型名称
	 */
	@ApiModelProperty(value = "操作类型名称", name = "actionTypeName")
	private boolean actionTypeName = true;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", name = "createDate")
	private boolean createDate = true;
	
	public boolean isIp() {
		return ip;
	}
	public void setIp(boolean ip) {
		this.ip = ip;
	}
	public boolean isUserName() {
		return userName;
	}
	public void setUserName(boolean userName) {
		this.userName = userName;
	}
	public boolean isActionType() {
		return actionType;
	}
	public void setActionType(boolean actionType) {
		this.actionType = actionType;
	}
	public boolean isActionTypeName() {
		return actionTypeName;
	}
	public void setActionTypeName(boolean actionTypeName) {
		this.actionTypeName = actionTypeName;
	}
	public boolean isCreateDate() {
		return createDate;
	}
	public void setCreateDate(boolean createDate) {
		this.createDate = createDate;
	}
	
}
