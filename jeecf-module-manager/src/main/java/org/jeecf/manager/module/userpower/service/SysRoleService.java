package org.jeecf.manager.module.userpower.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.userpower.dao.SysRoleDao;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.po.SysRolePO;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysRoleResult;
import org.springframework.stereotype.Service;

/**
 * 系统角色
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysRoleService extends BaseService<SysRoleDao, SysRolePO,SysRoleResult,SysRoleQuery, SysRole> {


}