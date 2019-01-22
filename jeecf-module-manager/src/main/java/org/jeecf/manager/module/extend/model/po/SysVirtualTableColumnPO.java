package org.jeecf.manager.module.extend.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableColumnQuery;
import org.jeecf.manager.module.extend.model.schema.SysVirtualTableColumnSchema;

/**
 * 虚表字段 PO
 * 
 * @author jianyiming
 *
 */
public class SysVirtualTableColumnPO extends AbstractEntityPO<SysVirtualTableColumnQuery> {

    public SysVirtualTableColumnPO(SysVirtualTableColumnQuery data) {
        this(data, new SysVirtualTableColumnSchema());
    }

    public SysVirtualTableColumnPO(SysVirtualTableColumnQuery data, SysVirtualTableColumnSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public SysVirtualTableColumnPO(Request<SysVirtualTableColumnQuery, SysVirtualTableColumnSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysVirtualTableColumnSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sys_virtual_table_column";
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
