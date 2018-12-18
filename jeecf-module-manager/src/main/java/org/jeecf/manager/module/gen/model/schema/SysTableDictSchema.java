package org.jeecf.manager.module.gen.model.schema;
/**
 * 系统表组字典schema
 * @author jianyiming
 *
 */
public class SysTableDictSchema {
	
	/**
	 * 主键
	 */
	private boolean id = true;
	/**
	 * 名称
	 */
	private boolean name = true;
	
	/**
	 * 表名
	 */
	private boolean tableName = true;
	
	/**
	 * 属性
	 */
	private boolean field = true;
	/**
	 * 注释
	 */
	private boolean comment = true;
	/**
	 * 描述
	 */
	private boolean description = true;
	/**
	 * 创建时间
	 */
	private boolean createDate = true;
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
	public boolean isTableName() {
		return tableName;
	}
	public void setTableName(boolean tableName) {
		this.tableName = tableName;
	}
	public boolean isField() {
		return field;
	}
	public void setField(boolean field) {
		this.field = field;
	}
	public boolean isDescription() {
		return description;
	}
	public void setDescription(boolean description) {
		this.description = description;
	}
	public boolean isCreateDate() {
		return createDate;
	}
	public void setCreateDate(boolean createDate) {
		this.createDate = createDate;
	}
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
}
