package org.jeecf.manager.module.userpower.service;

import org.jeecf.manager.common.service.OfficeAuthService;
import org.jeecf.manager.module.userpower.dao.SysUserDao;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysUserService extends OfficeAuthService<SysUserDao,SysUserPO,SysUserResult,SysUserQuery,SysUser> {
	
	
}