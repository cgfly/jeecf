package org.jeecf.manager.module.config.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.config.model.domain.SysUserDbsource;
import org.jeecf.manager.module.config.model.po.SysUserDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysUserDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysUserDbsourceResult;

/**
 * 用户数据源对应 dao
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysUserDbsourceDao extends Dao<SysUserDbsourcePO, SysUserDbsourceResult, SysUserDbsourceQuery, SysUserDbsource> {

    /**
     * 根据用户id 更新用户当前数据源
     * 
     * @param userId
     * @param dbsourceId
     * @return
     */
    public Integer updateByUserId(String userId, Integer dbsourceId);
}
