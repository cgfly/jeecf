package org.jeecf.manager.module.userpower.model.po;

import org.jeecf.common.model.Request;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.schema.SysPowerSchema;

/**
 * 系统权限
 * 
 * @author GloryJian
 * @version 1.0
 */
public class SysPowerPO extends AbstractEntityPO<SysPowerQuery> {

    public SysPowerPO(SysPowerQuery data) {
        super(data);
        this.setSchema(new SysPowerSchema());
    }

    public SysPowerPO(Request<SysPowerQuery, SysPowerSchema> request) {
        super(request);
        if (request.getSchema() == null) {
            this.setSchema(new SysPowerSchema());
        }
    }

    @Override
    public String getTableName() {
        return "sysPower";
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