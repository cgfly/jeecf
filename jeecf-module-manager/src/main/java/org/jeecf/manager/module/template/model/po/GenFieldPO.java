package org.jeecf.manager.module.template.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.schema.GenFieldSchema;

/**
 * 模版参数
 * @author GloryJian
 * @version 1.0
 */
public class GenFieldPO extends AbstractEntityPO<GenFieldQuery> {
	
	public GenFieldPO(GenFieldQuery data) {
		super(data);
		this.setSchema(new GenFieldSchema());
	}
	
	public GenFieldPO(Request<GenFieldQuery,GenFieldSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new GenFieldSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "genField";
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