package org.jeecf.manager.cache;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.springframework.stereotype.Component;
/**
 * session 缓存dao
 * @author jianyiming
 *
 */
@Component
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

	/**
	 * session 在redis过期时间是30分钟30*60
	 */
	private static int expireTime = 1800;

	private static String prefix = "shiro-session:";

	/**
	 * 创建session，保存到数据库
	 */
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		RedisCacheUtils.setCache(prefix + sessionId.toString(), session,expireTime);
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
			session = (Session) RedisCacheUtils.getCache(prefix + sessionId.toString());
		}
		return session;
	}

	/**
	 * 更新session的最后一次访问时间
	 */
	@Override
	protected void doUpdate(Session session) {
		super.doUpdate(session);
		String key = prefix + session.getId().toString();
		RedisCacheUtils.setCache(key, session, expireTime);
	}

	/**
	 * 删除session
	 */
	@Override
	protected void doDelete(Session session) {
		super.doDelete(session);
		RedisCacheUtils.delCache(prefix + session.getId().toString());
	}
}