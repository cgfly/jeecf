package org.jeecf.manager.module.userpower.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.userpower.model.query.SysRolePowerQuery;
import org.jeecf.manager.module.userpower.model.schema.SysRolePowerSchema;

/**
 * 系统角色权限
 * @author GloryJian
 * @version 1.0
 */
public class SysRolePowerPO extends AbstractEntityPO<SysRolePowerQuery> {
	
	public SysRolePowerPO(SysRolePowerQuery data) {
		super(data);
	}
	
	public SysRolePowerPO(Request<SysRolePowerQuery,SysRolePowerSchema> request) {
		super(request);
	}	
	
	@Override
	public String getTableName() {
		return "sysRolePower";
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