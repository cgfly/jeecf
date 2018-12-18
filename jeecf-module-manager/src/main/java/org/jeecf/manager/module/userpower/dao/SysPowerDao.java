package org.jeecf.manager.module.userpower.dao;


import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.po.SysPowerPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;

/**
 * 系统权限
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysPowerDao extends Dao<SysPowerPO,SysPowerResult,SysPowerQuery,SysPower>{


}