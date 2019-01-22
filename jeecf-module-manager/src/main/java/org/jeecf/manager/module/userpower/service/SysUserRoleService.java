package org.jeecf.manager.module.userpower.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.userpower.dao.SysUserRoleDao;
import org.jeecf.manager.module.userpower.model.domain.SysUserRole;
import org.jeecf.manager.module.userpower.model.po.SysUserRolePO;
import org.jeecf.manager.module.userpower.model.query.SysUserRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserRoleResult;
import org.springframework.stereotype.Service;

/**
 * 用户角色对照
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysUserRoleService extends BaseService<SysUserRoleDao, SysUserRolePO, SysUserRoleResult, SysUserRoleQuery, SysUserRole> {

}