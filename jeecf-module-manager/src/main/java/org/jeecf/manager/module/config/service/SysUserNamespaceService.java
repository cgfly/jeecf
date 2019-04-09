package org.jeecf.manager.module.config.service;

import org.jeecf.cache.annotation.Cache;
import org.jeecf.cache.annotation.FlushCache;
import org.jeecf.common.model.Response;
import org.jeecf.manager.cache.ClassCacheFlush;
import org.jeecf.manager.cache.QueryCacheLoadStore;
import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.config.dao.SysUserNamespaceDao;
import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
import org.jeecf.manager.module.config.model.po.SysUserNamespacePO;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysUserNamespaceResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户命名空间对应
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
@Cache(cacheLoadStore = QueryCacheLoadStore.class, cacheFlush = ClassCacheFlush.class, timeout = 60 * 60 * 24, open = false)
public class SysUserNamespaceService extends BaseService<SysUserNamespaceDao, SysUserNamespacePO, SysUserNamespaceResult, SysUserNamespaceQuery, SysUserNamespace> {

    @FlushCache
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> updateByUserId(String userId, Integer namespaceId) {
        return new Response<>(this.dao.updateByUserId(userId, namespaceId));
    }
}