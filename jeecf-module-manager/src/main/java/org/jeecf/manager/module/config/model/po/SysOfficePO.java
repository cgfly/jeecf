package org.jeecf.manager.module.config.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.config.model.query.SysOfficeQuery;
import org.jeecf.manager.module.config.model.schema.SysOfficeSchema;
/**
 * 组织结构 对照
 * @author jianyiming
 *
 */
public class SysOfficePO extends AbstractEntityPO<SysOfficeQuery>{
	
	public SysOfficePO(SysOfficeQuery data) {
		this(data,new SysOfficeSchema());
	}
	
	public SysOfficePO(SysOfficeQuery data,SysOfficeSchema schema) {
		super(data);
		this.setSchema(schema);
	}

	public SysOfficePO(Request<SysOfficeQuery,SysOfficeSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysOfficeSchema());
		}
	}

	@Override
	public String getTableName() {
		return "sysOffice";
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
