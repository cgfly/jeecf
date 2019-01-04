package org.jeecf.manager.module.extend.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableQuery;
import org.jeecf.manager.module.extend.model.schema.SysVirtualTableSchema;

/**
 * 虚表 PO
 * @author jianyiming
 *
 */
public class SysVirtualTablePO  extends AbstractEntityPO<SysVirtualTableQuery>{

	public SysVirtualTablePO(SysVirtualTableQuery data) {
		this(data,new SysVirtualTableSchema());
	}
	
	public SysVirtualTablePO(SysVirtualTableQuery data,SysVirtualTableSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysVirtualTablePO(Request<SysVirtualTableQuery,SysVirtualTableSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysVirtualTableSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sys_virtual_table";
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
