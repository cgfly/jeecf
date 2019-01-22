package org.jeecf.manager.module.template.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.schema.GenFieldColumnSchema;

/**
 * 模版参数列表
 * 
 * @author GloryJian
 * @version 1.0
 */
public class GenFieldColumnPO extends AbstractEntityPO<GenFieldColumnQuery> {

    public GenFieldColumnPO(GenFieldColumnQuery data) {
        this(data, new GenFieldColumnSchema());
    }

    public GenFieldColumnPO(GenFieldColumnQuery data, GenFieldColumnSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public GenFieldColumnPO(Request<GenFieldColumnQuery, GenFieldColumnSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new GenFieldColumnSchema());
        }
    }

    @Override
    public String getTableName() {
        return "genFieldColumn";
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