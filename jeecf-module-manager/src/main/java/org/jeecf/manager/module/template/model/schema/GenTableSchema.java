package org.jeecf.manager.module.template.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 代码表 schema
 * @author jianyiming
 *
 */
public class GenTableSchema {
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", name = "id")
	private boolean id = true;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private boolean name = true;
	
	/**
	 * 命名空间
	 */
	@ApiModelProperty(value="命名空间",name="sysNamespace")
	private boolean sysNamespaceId = true;

	/**
	 * 类名
	 */
	@ApiModelProperty(value = "类名", name = "className")
	private boolean className = true;

	/**
	 * 注释
	 */
	@ApiModelProperty(value = "注释", name = "comments")
	private boolean comment  = true;

	/**
	 * 父表id
	 */
	@ApiModelProperty(value = "父表id", name = "parentTable")
	private boolean parentTable  = true;

	/**
	 * 父表外键
	 */
	@ApiModelProperty(value = "父表外键", name = "parentTableFk")
	private boolean parentTableFk = true;
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

	public boolean isName() {
		return name;
	}

	public void setName(boolean name) {
		this.name = name;
	}

	public boolean isSysNamespaceId() {
		return sysNamespaceId;
	}

	public void setSysNamespaceId(boolean sysNamespaceId) {
		this.sysNamespaceId = sysNamespaceId;
	}

	public boolean isClassName() {
		return className;
	}

	public void setClassName(boolean className) {
		this.className = className;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean isParentTable() {
		return parentTable;
	}

	public void setParentTable(boolean parentTable) {
		this.parentTable = parentTable;
	}

	public boolean isParentTableFk() {
		return parentTableFk;
	}

	public void setParentTableFk(boolean parentTableFk) {
		this.parentTableFk = parentTableFk;
	}

	public boolean isUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}
	
}
