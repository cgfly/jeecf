package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织机构 schema
 * @author jianyiming
 *
 */
public class SysOfficeSchema {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",name="id")
	private boolean id = true;
	
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",name="name")
	private boolean name = true;
	
	/**
	 * 英文名称
	 */
	@ApiModelProperty(value="英文名称",name="enname")
	private boolean enname = true;
	
	/**
	 * 父层
	 */
	@ApiModelProperty(value="父层",name="parent")
	private boolean parent = true;
	
	/**
	 * 父层索引
	 */
	@ApiModelProperty(value="父层索引",name="parentId")
	private boolean parentId = true;
	
	/**
	 * 父层索引集
	 */
	@ApiModelProperty(value="所有父层索引",name="parentIds")
	private boolean parentIds = true;
	
	/**
	 * 等级
	 */
	@ApiModelProperty(value="等级",name="level")
	private boolean level = true;
	
	/**
	 * 同级排序
	 */
	@ApiModelProperty(value="同级排序",name="sort")
	private boolean sort = true;

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

	public boolean isEnname() {
		return enname;
	}

	public void setEnname(boolean enname) {
		this.enname = enname;
	}

	public boolean isParentId() {
		return parentId;
	}

	public void setParentId(boolean parentId) {
		this.parentId = parentId;
	}

	public boolean isParentIds() {
		return parentIds;
	}

	public void setParentIds(boolean parentIds) {
		this.parentIds = parentIds;
	}

	public boolean isLevel() {
		return level;
	}

	public void setLevel(boolean level) {
		this.level = level;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

}
