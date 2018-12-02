package org.jeecf.manager.gen.model;

import java.util.List;
/**
 * gen模版实体
 * @author jianyiming
 *
 */
public class GenTemplateEntity {
	/**
	 * 模版名称
	 */
	private String name;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件名称
	 */
	private String fileName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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
