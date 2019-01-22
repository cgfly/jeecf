package org.jeecf.manager.module.userpower.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.po.SysRolePO;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysRoleResult;

/**
 * 系统角色
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysRoleDao extends Dao<SysRolePO, SysRoleResult, SysRoleQuery, SysRole> {

}