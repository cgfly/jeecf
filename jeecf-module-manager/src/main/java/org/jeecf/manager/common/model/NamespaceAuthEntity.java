package org.jeecf.manager.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 命名空间安全验证实体
 * @author jianyiming
 *
 */
public class NamespaceAuthEntity extends BaseEntity{
	
	public NamespaceAuthEntity() {
	}
	
	public NamespaceAuthEntity(String id) {
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
