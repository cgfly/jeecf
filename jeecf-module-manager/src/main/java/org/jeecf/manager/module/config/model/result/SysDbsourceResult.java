package org.jeecf.manager.module.config.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.config.model.domain.SysDbsource;

import io.swagger.annotations.ApiModelProperty;
/**
 * 系统db数据源库返回实体
 * @author jianyiming
 *
 */
public class SysDbsourceResult extends SysDbsource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 可用性名称
	 */
	@ApiModelProperty(value = "可用性名称", name = "usableName")
	private String usableName;

	public String getUsableName() {
		return usableName;
	}

	public void setUsableName(String usableName) {
		this.usableName = usableName;
	}
	
}
