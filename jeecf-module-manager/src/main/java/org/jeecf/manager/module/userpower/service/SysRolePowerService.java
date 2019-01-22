package org.jeecf.manager.module.userpower.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.userpower.dao.SysRolePowerDao;
import org.jeecf.manager.module.userpower.model.domain.SysRolePower;
import org.jeecf.manager.module.userpower.model.po.SysRolePowerPO;
import org.jeecf.manager.module.userpower.model.query.SysRolePowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysRolePowerResult;
import org.springframework.stereotype.Service;

/**
 * 系统角色权限
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysRolePowerService extends BaseService<SysRolePowerDao, SysRolePowerPO, SysRolePowerResult, SysRolePowerQuery, SysRolePower> {

}