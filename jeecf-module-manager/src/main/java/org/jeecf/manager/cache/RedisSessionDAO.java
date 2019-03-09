package org.jeecf.manager.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * session 缓存dao
 * 
 * @author jianyiming
 *
 */
@Component
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    public RedisSessionDAO(CacheManager redisShiroCacheManager) {
        this.setCacheManager(redisShiroCacheManager);
    }

    /**
     * session 在redis过期时间是30分钟30*60
     */
    private static int EXPIRE_TIME = 1800;

    private static String PREFIX = "shiro-session:";

    /**
     * 创建session，保存到数据库
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        RedisCacheUtils.setCache(PREFIX + sessionId.toString(), session, EXPIRE_TIME);
        return sessionId;
    }

    /**
     * 获取session
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) RedisCacheUtils.getCache(PREFIX + sessionId.toString());
        }
        return session;
    }

    /**
     * 更新session的最后一次访问时间
     */
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = PREFIX + session.getId().toString();
        RedisCacheUtils.setCache(key, session, EXPIRE_TIME);
    }

    /**
     * 删除session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        RedisCacheUtils.delCache(PREFIX + session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Session> sessions = new ArrayList<>();
        Set<Object> keys = this.getCacheManager().getCache(this.getActiveSessionsCacheName()).keys();
        keys.forEach(key -> {
            Object obj = RedisCacheUtils.getCache(key.toString());
            sessions.add((Session) obj);
        });
        return sessions;
    }
}