package org.jeecf.manager.module.userpower.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.schema.SysRoleSchema;

/**
 * 系统角色
 * @author GloryJian
 * @version 1.0
 */
public class SysRolePO extends AbstractEntityPO<SysRoleQuery> {
	
	public SysRolePO(SysRoleQuery data) {
		super(data);
		this.setSchema(new SysRoleSchema());
	}
	
	public SysRolePO(Request<SysRoleQuery,SysRoleSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysRoleSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysRole";
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