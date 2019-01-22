package org.jeecf.manager.module.userpower.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.userpower.model.domain.SysRolePower;
import org.jeecf.manager.module.userpower.model.po.SysRolePowerPO;
import org.jeecf.manager.module.userpower.model.query.SysRolePowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysRolePowerResult;

/**
 * 系统角色权限
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysRolePowerDao extends Dao<SysRolePowerPO, SysRolePowerResult, SysRolePowerQuery, SysRolePower> {

}