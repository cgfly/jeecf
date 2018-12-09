package org.jeecf.manager.common.model;

/**
 * 组织结构安全验证实体
 * @author jianyiming
 *
 */
public class OfficeAuthEntity extends BaseEntity{
	
	public OfficeAuthEntity() {
	}
	
	public OfficeAuthEntity(String id) {
		super(id);
	}
	
	private Integer sysOfficeId;

	public Integer getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(Integer sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	

}
