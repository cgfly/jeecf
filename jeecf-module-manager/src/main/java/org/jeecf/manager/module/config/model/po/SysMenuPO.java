package org.jeecf.manager.module.config.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.schema.SysMenuSchema;

/**
 * 系统菜单PO 实体
 * 
 * @author jianyiming
 *
 */
public class SysMenuPO extends AbstractEntityPO<SysMenuQuery> {

    public SysMenuPO(SysMenuQuery data) {
        this(data, new SysMenuSchema());
    }

    public SysMenuPO(SysMenuQuery data, SysMenuSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public SysMenuPO(Request<SysMenuQuery, SysMenuSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysMenuSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sysMenu";
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