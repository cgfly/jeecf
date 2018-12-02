package org.jeecf.manager.module.operation.model.po;

import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.operation.model.query.CustomerActionLogQuery;
import org.jeecf.manager.module.operation.model.schema.CustomerActionLogSchema;
/**
 * 客户操作日志 PO 
 * @author jianyiming
 *
 */
public class CustomerActionLogPO extends AbstractEntityPO<CustomerActionLogQuery> {

	public CustomerActionLogPO(CustomerActionLogQuery data) {
		this(data,new CustomerActionLogSchema());
	}
	
	public CustomerActionLogPO(CustomerActionLogQuery data,CustomerActionLogSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public CustomerActionLogPO(Request<CustomerActionLogQuery,CustomerActionLogSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new CustomerActionLogSchema());
		}
	}

	@Override
	public String getTableName() {
		return "customerActionLog";
	}

	@Override
	public void buildSorts() {
		super.buildSorts(this.getTableName());
	}

	@Override
	public void buildContains() {
		super.buildSorts(this.getTableName());
	}

}
