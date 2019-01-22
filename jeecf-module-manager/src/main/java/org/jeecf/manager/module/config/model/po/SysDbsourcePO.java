package org.jeecf.manager.module.config.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.schema.SysDbsourceSchema;

/**
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
public class SysDbsourcePO extends AbstractEntityPO<SysDbsourceQuery> {

    public SysDbsourcePO(SysDbsourceQuery data) {
        this(data, new SysDbsourceSchema());
    }

    public SysDbsourcePO(SysDbsourceQuery data, SysDbsourceSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public SysDbsourcePO(Request<SysDbsourceQuery, SysDbsourceSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysDbsourceSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sysDbsource";
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