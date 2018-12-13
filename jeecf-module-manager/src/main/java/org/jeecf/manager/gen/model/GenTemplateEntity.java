package org.jeecf.manager.gen.model;

import java.util.List;
/**
 * gen模版实体
 * @author jianyiming
 *
 */
public class GenTemplateEntity {

	/**
	 * 模版id
	 */
	private Integer templateId;
	/**
	 * 表id
	 */
	private Integer tableId;
	/**
	 * 参数
	 */
	private List<GenParams> params;

	public List<GenParams> getParams() {
		return params;
	}

	public void setParams(List<GenParams> params) {
		this.params = params;
	}
	
	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	
	
	

}
