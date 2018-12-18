package org.jeecf.manager.module.template.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.schema.GenTableColumnSchema;
/**
 * 业务表字段 PO实体
 * @author jianyiming
 *
 */
public class GenTableColumnPO extends AbstractEntityPO<GenTableColumnQuery>{

	public GenTableColumnPO(GenTableColumnQuery data) {
		this(data,new GenTableColumnSchema());
	}
	
	public GenTableColumnPO(GenTableColumnQuery data,GenTableColumnSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public GenTableColumnPO(Request<GenTableColumnQuery,GenTableColumnSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new GenTableColumnSchema());
		}
	}
	
	@Override
	public String getTableName() {
		return "genTableColumn";
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
