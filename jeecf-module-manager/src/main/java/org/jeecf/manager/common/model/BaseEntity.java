package org.jeecf.manager.common.model;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.jeecf.common.model.AbstractEntity;
import org.jeecf.manager.common.utils.RedisCacheUtils;

/**
 * 基础实体
 * 
 * @author jianyiming
 *
 */
public class BaseEntity extends AbstractEntity {

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this.setId(id);
    }

    @Override
    public void preInsert() {

        if (this.getCreateBy() == null) {
            String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
            String id = (String) RedisCacheUtils.getSysCache(sessionId);
            this.setCreateBy(id);
        }
        if (this.getCreateDate() == null) {
            this.setCreateDate(new Date());
        }
        this.setUpdateBy(this.getCreateBy());
        this.setUpdateDate(this.getCreateDate());

    }

    @Override
    public void preUpdate() {
        if (this.getUpdateBy() == null) {
            String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
            String id = (String) RedisCacheUtils.getSysCache(sessionId);
            this.setUpdateBy(id);
        }
        if (this.getUpdateDate() == null) {
            this.setUpdateDate(new Date());
        }
    }

}
