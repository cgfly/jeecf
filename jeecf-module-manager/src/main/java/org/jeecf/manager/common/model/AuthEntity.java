package org.jeecf.manager.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 数据安全实体
 * @author jianyiming
 *
 */
public class AuthEntity extends BaseEntity{
	
	public AuthEntity() {
	}
	
	public AuthEntity(String id) {
		super(id);
	}
	/**
	 * 命名空间
	 */
	@ApiModelProperty(value="命名空间",name="sysNamespace")
	private Integer sysNamespaceId;

	public Integer getSysNamespaceId() {
		return sysNamespaceId;
	}

	public void setSysNamespaceId(Integer sysNamespaceId) {
		this.sysNamespaceId = sysNamespaceId;
	}
	
	
	
	
}
