package org.jeecf.manager.module.template.model.result;

import java.io.Serializable;

import org.jeecf.common.enums.IfTypeEnum;
import org.jeecf.common.gen.enums.FormTypeEnum;
import org.jeecf.common.gen.enums.QueryTypeEnum;
import org.jeecf.manager.module.template.model.domain.GenTableColumn;

import io.swagger.annotations.ApiModelProperty;

/**
 * 表字段返回实体
 * 
 * @author jianyiming
 *
 */
public class GenTableColumnResult extends GenTableColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 是否为插入字段（1：插入字段）
	 */
	@ApiModelProperty(value = "是否插入名称", name = "isInsert")
	private String isInsertName;

	/**
	 * 是否编辑字段（1：编辑字段）
	 */
	@ApiModelProperty(value = "是否修改名称", name = "isEdit")
	private String isEditName;

	/**
	 * 是否列表字段（1：列表字段）
	 */
	@ApiModelProperty(value = "是否列表展现名称", name = "isList")
	private String isListName;

	/**
	 * 是否查询字段（1：查询字段）
	 */
	@ApiModelProperty(value = "是否查询字段名称", name = "isQuery")
	private String isQueryName;

	/**
	 * 是否空值
	 */
	@ApiModelProperty(value = "空值名称", name = "isNull")
	private String isNullName;

	/**
	 * 是否主键
	 */
	@ApiModelProperty(value = "是否主键名称", name = "isKey")
	private String isKeyName;

	/**
	 * 查询类型名称
	 */
	@ApiModelProperty(value = "查询类型名称", name = "queryTypeName")
	private String queryTypeName;

	/**
	 * 表单类型名称
	 */
	@ApiModelProperty(value = "表单类型名称", name = "formTypeName")
	private String formTypeName;

	public String getIsInsertName() {
		return isInsertName;
	}

	public void setIsInsertName(String isInsertName) {
		this.isInsertName = isInsertName;
	}

	public String getIsEditName() {
		return isEditName;
	}

	public void setIsEditName(String isEditName) {
		this.isEditName = isEditName;
	}

	public String getIsListName() {
		return isListName;
	}

	public void setIsListName(String isListName) {
		this.isListName = isListName;
	}

	public String getIsQueryName() {
		return isQueryName;
	}

	public void setIsQueryName(String isQueryName) {
		this.isQueryName = isQueryName;
	}

	public String getIsNullName() {
		return isNullName;
	}

	public void setIsNullName(String isNullName) {
		this.isNullName = isNullName;
	}

	public String getIsKeyName() {
		return isKeyName;
	}

	public void setIsKeyName(String isKeyName) {
		this.isKeyName = isKeyName;
	}

	public String getQueryTypeName() {
		return queryTypeName;
	}

	public void setQueryTypeName(String queryTypeName) {
		this.queryTypeName = queryTypeName;
	}

	public String getFormTypeName() {
		return formTypeName;
	}

	public void setFormTypeName(String formTypeName) {
		this.formTypeName = formTypeName;
	}

	public void coverField(GenTableColumnResult genTableColumnResult) {
		if (genTableColumnResult.getIsKey() != null) {
			this.setIsKeyName(IfTypeEnum.getName(genTableColumnResult.getIsKey()));
		}
		if (genTableColumnResult.getIsNull() != null) {
			this.setIsNullName(IfTypeEnum.getName(genTableColumnResult.getIsNull()));
		}
		if (genTableColumnResult.getIsEdit() != null) {
			this.setIsEditName(IfTypeEnum.getName(genTableColumnResult.getIsEdit()));
		}
		if (genTableColumnResult.getIsInsert() != null) {
			this.setIsInsertName(IfTypeEnum.getName(genTableColumnResult.getIsInsert()));
		}
		if (genTableColumnResult.getIsList() != null) {
			this.setIsListName(IfTypeEnum.getName(genTableColumnResult.getIsList()));
		}
		if (genTableColumnResult.getIsQuery() != null) {
			this.setIsQueryName(IfTypeEnum.getName(genTableColumnResult.getIsQuery()));
		}
		if (genTableColumnResult.getQueryType() != null) {
			this.setQueryTypeName(QueryTypeEnum.getName(genTableColumnResult.getQueryType()));
		}
		if (genTableColumnResult.getFormType() != null) {
			this.setFormTypeName(FormTypeEnum.getName(genTableColumnResult.getFormType()));
		}
	}
}
