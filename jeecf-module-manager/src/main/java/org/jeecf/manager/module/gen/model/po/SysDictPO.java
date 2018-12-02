package org.jeecf.manager.module.gen.model.po;


import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.schema.SysDictSchema;

/**
 * 数据字典
 * @author GloryJian
 *
 */
public class SysDictPO extends AbstractEntityPO<SysDictQuery> {
	
	public SysDictPO(SysDictQuery data) {
		this(data,new SysDictSchema());
	}
	
	public SysDictPO(SysDictQuery data,SysDictSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysDictPO(Request<SysDictQuery,SysDictSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysDictSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysDict";
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