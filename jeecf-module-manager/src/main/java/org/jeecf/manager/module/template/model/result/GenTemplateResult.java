package org.jeecf.manager.module.template.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.template.model.domain.GenTemplate;

import io.swagger.annotations.ApiModelProperty;
/**
 * 代码生成模版返回实体
 * @author jianyiming
 *
 */
public class GenTemplateResult extends GenTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 代码属性名称
	 */
	@ApiModelProperty(value = "代码属性名称", name = "genFieldName")
	private String genFieldName;

	public String getGenFieldName() {
		return genFieldName;
	}

	public void setGenFieldName(String genFieldName) {
		this.genFieldName = genFieldName;
	}
	

}
