package org.jeecf.manager.module.userpower.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.schema.SysUserSchema;

/**
 * 系统用户
 * @author GloryJian
 * @version 1.0
 */
public class SysUserPO extends AbstractEntityPO<SysUserQuery> {
	
	public SysUserPO(SysUserQuery data) {
		super(data);
		this.setSchema(new SysUserSchema());
	}
	
	public SysUserPO(Request<SysUserQuery,SysUserSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysUserSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysUser";
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