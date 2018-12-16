package org.jeecf.manager.module.gen.model.po;

import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.gen.model.query.SysTableDictQuery;
import org.jeecf.manager.module.gen.model.schema.SysTableDictSchema;
/**
 * 表组字典 PO
 * @author jianyiming
 *
 */
public class SysTableDictPO extends AbstractEntityPO<SysTableDictQuery>{

	public SysTableDictPO(SysTableDictQuery data) {
		this(data,new SysTableDictSchema());
	}
	
	public SysTableDictPO(SysTableDictQuery data,SysTableDictSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysTableDictPO(Request<SysTableDictQuery,SysTableDictSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysTableDictSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysTableDict";
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
