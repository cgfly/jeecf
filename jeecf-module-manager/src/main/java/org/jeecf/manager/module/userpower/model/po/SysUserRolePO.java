package org.jeecf.manager.module.userpower.model.po;


import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.userpower.model.query.SysUserRoleQuery;
import org.jeecf.manager.module.userpower.model.schema.SysUserRoleSchema;

/**
 * 用户角色对照
 * @author GloryJian
 * @version 1.0
 */
public class SysUserRolePO extends AbstractEntityPO<SysUserRoleQuery> {
	
	public SysUserRolePO(SysUserRoleQuery data) {
		super(data);
	}
	
	public SysUserRolePO(Request<SysUserRoleQuery,SysUserRoleSchema> request) {
		super(request);
	}	
	
	@Override
	public String getTableName() {
		return "sysUserRole";
	}

	@Override
	public void buildSorts() {
		super.buildSorts(this.getTableName());
	}
	
	@Override
	public void buildContains() {
		super.buildContains(this.getTableName());
	}
	
	
}