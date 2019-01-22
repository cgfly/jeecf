package org.jeecf.manager.module.extend.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.schema.SysOsgiPluginSchema;

/**
 * OSGI 插件PO
 * 
 * @author jianyiming
 *
 */
public class SysOsgiPluginPO extends AbstractEntityPO<SysOsgiPluginQuery> {

    public SysOsgiPluginPO(SysOsgiPluginQuery data) {
        this(data, new SysOsgiPluginSchema());
    }

    public SysOsgiPluginPO(SysOsgiPluginQuery data, SysOsgiPluginSchema schema) {
        super(data);
        this.setSchema(schema);
    }

    public SysOsgiPluginPO(Request<SysOsgiPluginQuery, SysOsgiPluginSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysOsgiPluginSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sys_osgi_plugin";
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
