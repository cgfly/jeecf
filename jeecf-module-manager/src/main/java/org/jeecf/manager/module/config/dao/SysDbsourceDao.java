package org.jeecf.manager.module.config.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;

/**
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysDbsourceDao extends Dao<SysDbsourcePO, SysDbsourceResult, SysDbsourceQuery, SysDbsource> {

}