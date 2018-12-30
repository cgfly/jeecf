package org.jeecf.manager.module.gen.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.gen.model.query.SysTreeDictQuery;
import org.jeecf.manager.module.gen.model.schema.SysTreeDictSchema;

/**
 * 树组字典 PO
 * @author jianyiming
 *
 */
public class SysTreeDictPO extends AbstractEntityPO<SysTreeDictQuery>{
	
	public SysTreeDictPO(SysTreeDictQuery data) {
		this(data,new SysTreeDictSchema());
	}
	
	public SysTreeDictPO(SysTreeDictQuery data,SysTreeDictSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysTreeDictPO(Request<SysTreeDictQuery,SysTreeDictSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysTreeDictSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysTreeDict";
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
