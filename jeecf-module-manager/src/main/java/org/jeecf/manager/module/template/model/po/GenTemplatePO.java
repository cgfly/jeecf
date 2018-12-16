package org.jeecf.manager.module.template.model.po;


import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.schema.GenTemplateSchema;

/**
 * 模版配置
 * @author GloryJian
 * @version 1.0
 */
public class GenTemplatePO extends AbstractEntityPO<GenTemplateQuery> {
	
	public GenTemplatePO(GenTemplateQuery data) {
		super(data);
		this.setSchema(new GenTemplateSchema());
	}
	
	public GenTemplatePO(Request<GenTemplateQuery,GenTemplateSchema> request) {
		super(request);
		if(request.getSchema() == null) {
			this.setSchema(new GenTemplateSchema());
		}
	}	
	

	@Override
	public String getTableName() {
		return "genTemplate";
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