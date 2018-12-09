package org.jeecf.manager.common.model;

import javax.validation.constraints.NotNull;

import org.jeecf.manager.validate.groups.Add;

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

	@NotNull(message="组织结构输入不能为空",groups= {Add.class})
	public Integer getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(Integer sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	

}
