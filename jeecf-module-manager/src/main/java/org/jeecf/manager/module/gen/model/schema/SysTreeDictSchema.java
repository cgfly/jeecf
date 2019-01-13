package org.jeecf.manager.module.gen.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 树组字典 schema
 * @author jianyiming
 *
 */
public class SysTreeDictSchema {
	
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
	 * 左等式
	 */
	@ApiModelProperty(value="左等式",name="leftEqual")
	private boolean leftEqual = true;
	/**
	 * 事件
	 */
	@ApiModelProperty(value="事件",name="event")
	private boolean event = true;
	/**
	 * 右等式
	 */
	@ApiModelProperty(value="右等式",name="rightEqual")
	private boolean rightEqual = true;
	/**
	 * 产出
	 */
	@ApiModelProperty(value="产出",name="result")
	private boolean result = true;
	
	/**
	 * 组别
	 */
	@ApiModelProperty(value="组别",name="group")
	private boolean groupName = true;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value="描述",name="description")
	private boolean description = true;
	
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

	public boolean isEvent() {
		return event;
	}

	public void setEvent(boolean event) {
		this.event = event;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean isGroupName() {
		return groupName;
	}

	public void setGroupName(boolean groupName) {
		this.groupName = groupName;
	}

	public boolean isDescription() {
		return description;
	}

	public void setDescription(boolean description) {
		this.description = description;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
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
	
}
