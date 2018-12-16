package org.jeecf.manager.module.config.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.schema.SysUserNamespaceSchema;

/**
 * 用户命名空间对应
 * @author GloryJian
 * @version 1.0
 */
public class SysUserNamespacePO extends AbstractEntityPO<SysUserNamespaceQuery> {
	
	public SysUserNamespacePO(SysUserNamespaceQuery data) {
		this(data,new SysUserNamespaceSchema());
	}
	
	public SysUserNamespacePO(SysUserNamespaceQuery data,SysUserNamespaceSchema schema) {
		super(data);
		this.setSchema(schema);
	}
	
	public SysUserNamespacePO(Request<SysUserNamespaceQuery,SysUserNamespaceSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new SysUserNamespaceSchema());
		}
	}	
	
	@Override
	public String getTableName() {
		return "sysUserNamespace";
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