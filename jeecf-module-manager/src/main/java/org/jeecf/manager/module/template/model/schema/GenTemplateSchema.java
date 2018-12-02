package org.jeecf.manager.module.template.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 代码模版 schema
 * @author jianyiming
 *
 */
public class GenTemplateSchema {
	
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
	 * 语言
	 */
	@ApiModelProperty(value = "语言", name = "language")
	private boolean language = true;
	/**
	 * 关联gen_field
	 */
	@ApiModelProperty(value = "关联gen_field", name = "genFieldId")
	private boolean genFieldId = true;
	/**
	 * 模版文件基础路径
	 */
	@ApiModelProperty(value = "模版文件基础路径", name = "fileBasePath")
	private boolean fileBasePath = true;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", name = "descrition")
	private boolean descrition = true;
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
	public boolean isLanguage() {
		return language;
	}
	public void setLanguage(boolean language) {
		this.language = language;
	}
	public boolean isGenFieldId() {
		return genFieldId;
	}
	public void setGenFieldId(boolean genFieldId) {
		this.genFieldId = genFieldId;
	}
	public boolean isFileBasePath() {
		return fileBasePath;
	}
	public void setFileBasePath(boolean fileBasePath) {
		this.fileBasePath = fileBasePath;
	}
	public boolean isDescrition() {
		return descrition;
	}
	public void setDescrition(boolean descrition) {
		this.descrition = descrition;
	}
	public boolean isUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}
	
}
