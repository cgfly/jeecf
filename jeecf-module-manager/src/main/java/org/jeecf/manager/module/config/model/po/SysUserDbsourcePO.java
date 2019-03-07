package org.jeecf.manager.module.config.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.config.model.query.SysUserDbsourceQuery;
import org.jeecf.manager.module.config.model.schema.SysUserDbsourceSchema;
import org.jeecf.manager.module.config.model.schema.SysUserNamespaceSchema;

/**
 * 用户数据源对应
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SysUserDbsourcePO extends AbstractEntityPO<SysUserDbsourceQuery> {

    public SysUserDbsourcePO(SysUserDbsourceQuery data) {
        this(data, new SysUserDbsourceSchema());
    }

    public SysUserDbsourcePO(SysUserDbsourceQuery data, SysUserDbsourceSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public SysUserDbsourcePO(Request<SysUserDbsourceQuery, SysUserDbsourceSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysUserNamespaceSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sysUserDbsource";
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
