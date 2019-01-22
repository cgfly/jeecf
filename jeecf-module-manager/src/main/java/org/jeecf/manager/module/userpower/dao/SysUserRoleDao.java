package org.jeecf.manager.module.userpower.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.userpower.model.domain.SysUserRole;
import org.jeecf.manager.module.userpower.model.po.SysUserRolePO;
import org.jeecf.manager.module.userpower.model.query.SysUserRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserRoleResult;

/**
 * 用户角色对照
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysUserRoleDao extends Dao<SysUserRolePO, SysUserRoleResult, SysUserRoleQuery, SysUserRole> {

}