package org.jeecf.manager.module.userpower.service;

import org.jeecf.cache.annotation.Cache;
import org.jeecf.manager.cache.ClassCacheFlush;
import org.jeecf.manager.cache.QueryCacheLoadStore;
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
@Cache(cacheLoadStore = QueryCacheLoadStore.class, cacheFlush = ClassCacheFlush.class, timeout = 60 * 60 * 24, open = false)
public class SysRolePowerService extends BaseService<SysRolePowerDao, SysRolePowerPO, SysRolePowerResult, SysRolePowerQuery, SysRolePower> {

}