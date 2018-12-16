package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 命名空间 schema
 * @author jianyiming
 *
 */
public class SysNamespaceSchema {
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", name = "id")
	private  boolean id = true;
	/**
	 * 权限
	 */
	@ApiModelProperty(value="权限",name="permission")
	private boolean permission = true;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private boolean name = true;
	
	/**
	 * 逻辑删除
	 */
	@ApiModelProperty(value = "逻辑删除", name = "delFlag")
	private boolean delFlag = true;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", name = "description")
	private boolean description = true;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "更新时间", name = "updateDate")
	private boolean updateDate = true;

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public boolean isName() {
		return name;
	}

	public void setName(boolean name) {
		this.name = name;
	}

	public boolean isDescription() {
		return description;
	}

	public void setDescription(boolean description) {
		this.description = description;
	}

	public boolean isUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
}
