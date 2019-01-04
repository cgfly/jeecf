package org.jeecf.manager.module.extend.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 虚表字段 schema
 * @author jianyiming
 *
 */
public class SysVirtualTableColumnSchema {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",name="id")
	private boolean id = true;
	
	/**
	 * 字段名
	 */
	@ApiModelProperty(value="字段名",name="name")
	private boolean name = true;
	/**
	 * 虚表id
	 */
	@ApiModelProperty(value="虚表id",name="sysVirtualTableId")
	private boolean sysVirtualTableId;
	/**
	 * 注释
	 */
	@ApiModelProperty(value="注释",name="comment")
	private boolean comment = true;
	/**
	 * 类型
	 */
	@ApiModelProperty(value="类型",name="type")
	private boolean type = true;
	/**
	 * 长度
	 */
	@ApiModelProperty(value="长度",name="length")
	private boolean length = true;
	/**
	 * 小数
	 */
	@ApiModelProperty(value="小数",name="decimal")
	private boolean decimalLength = true;
	/**
	 * 不为空
	 */
	@ApiModelProperty(value="不为空",name="isNotNull")
	private boolean isNotNull = true;
	/**
	 * 默认值
	 */
	@ApiModelProperty(value="默认值",name="defaultValue")
	private boolean defaultValue = true;
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",name="isKey")
	private boolean isKey = true;
	/**
	 * 自增
	 */
	@ApiModelProperty(value="自增",name="isAuto")
	private boolean isAuto = true;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",name="createDate")
	private boolean createDate = true;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",name="updateDate")
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
	public boolean isSysVirtualTableId() {
		return sysVirtualTableId;
	}
	public void setSysVirtualTableId(boolean sysVirtualTableId) {
		this.sysVirtualTableId = sysVirtualTableId;
	}
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public boolean isLength() {
		return length;
	}
	public void setLength(boolean length) {
		this.length = length;
	}
	public boolean isDecimalLength() {
		return decimalLength;
	}
	public void setDecimalLength(boolean decimalLength) {
		this.decimalLength = decimalLength;
	}
	public boolean isNotNull() {
		return isNotNull;
	}
	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}
	public boolean isDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean isAuto() {
		return isAuto;
	}
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	public boolean isCreateDate() {
		return createDate;
	}
	public void setCreateDate(boolean createDate) {
		this.createDate = createDate;
	}
	public boolean isUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}
	
}
