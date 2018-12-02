package org.jeecf.manager.module.template.model.po;

import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.schema.GenTableSchema;
/**
 * 业务表 PO 实体
 * @author jianyiming
 *
 */
public class GenTablePO extends AbstractEntityPO<GenTableQuery>{

	public GenTablePO(GenTableQuery data) {
		super(data);
		this.setSchema(new GenTableSchema());
	}
	
	public GenTablePO(Request<GenTableQuery,GenTableSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new GenTableSchema());
		}
	}
	
	@Override
	public String getTableName() {
		return "genTable";
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


