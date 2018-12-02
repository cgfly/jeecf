package org.jeecf.manager.module.template.model.schema;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * 模版参数
 * @author jianyiming
 *
 */
public class GenFieldSchema {
	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键",name="id")
	private boolean id = true;
	/**
	 *  命名空间id
	 */
	@ApiModelProperty(value="命名空间",name="sysNamespace")
	private boolean sysNamespaceId = true;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private boolean name = true;
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
	public boolean isSysNamespaceId() {
		return sysNamespaceId;
	}
	public void setSysNamespaceId(boolean sysNamespaceId) {
		this.sysNamespaceId = sysNamespaceId;
	}
	public boolean isName() {
		return name;
	}
	public void setName(boolean name) {
		this.name = name;
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
