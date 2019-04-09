package org.jeecf.manager.module.config.service;

import org.jeecf.cache.annotation.Cache;
import org.jeecf.cache.annotation.FlushCache;
import org.jeecf.common.model.Response;
import org.jeecf.manager.cache.ClassCacheFlush;
import org.jeecf.manager.cache.QueryCacheLoadStore;
import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.config.dao.SysUserDbsourceDao;
import org.jeecf.manager.module.config.model.domain.SysUserDbsource;
import org.jeecf.manager.module.config.model.po.SysUserDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysUserDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysUserDbsourceResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户数据源对应 service
 * 
 * @author jianyiming
 * @version 2.0
 */
@Service
@Cache(cacheLoadStore = QueryCacheLoadStore.class, cacheFlush = ClassCacheFlush.class, timeout = 60 * 60 * 24,open=false)
public class SysUserDbsourceService extends BaseService<SysUserDbsourceDao, SysUserDbsourcePO, SysUserDbsourceResult, SysUserDbsourceQuery, SysUserDbsource> {

    @FlushCache
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> updateByUserId(String userId, Integer dbsourceId) {
        return new Response<>(this.dao.updateByUserId(userId, dbsourceId));
    }

}
