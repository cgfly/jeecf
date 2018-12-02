package org.jeecf.manager.module.config.model.po;


import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Request;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.schema.SysNamespaceSchema;

/**
 * 系统命名空间
 * @author GloryJian
 * @version 1.0
 */
public class SysNamespacePO extends AbstractEntityPO<SysNamespaceQuery> {
	
	public SysNamespacePO(SysNamespaceQuery data) {
		this(data,new SysNamespaceSchema());
	}
	
	public SysNamespacePO(SysNamespaceQuery data,SysNamespaceSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysNamespacePO(Request<SysNamespaceQuery,SysNamespaceSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysNamespaceSchema());
		}
	}	
	

	@Override
	public String getTableName() {
		return "sysNamespace";
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