package org.jeecf.manager.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.jeecf.manager.common.utils.RedisCacheUtils;

/**
 * shiro 缓存
 * 
 * @author jianyiming
 *
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class ShiroCache<K, V> implements Cache<K, V> {

    private static final String REDIS_SHIRO_CACHE = "shiro-cache:";
    private String cacheKey;
    private int globExpire = 30 * 60;

    public ShiroCache(String name) {
        this.cacheKey = REDIS_SHIRO_CACHE + name + ":";
    }

    @Override
    public V get(K key) throws CacheException {
        RedisCacheUtils.expire(getCacheKey(key), globExpire);
        return (V) RedisCacheUtils.getCache(getCacheKey(key));
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V old = get(key);
        RedisCacheUtils.setCache(getCacheKey(key), value, globExpire);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        V old = get(key);
        RedisCacheUtils.delCache(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        RedisCacheUtils.delCache(strkeys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        Set<K> kSet = new HashSet<>();
        Set<String> arraySet = RedisCacheUtils.keys(getCacheKey("*"));
        if (CollectionUtils.isNotEmpty(arraySet)) {
            for (String key : arraySet) {
                kSet.add((K) key);
            }
        }
        return kSet;
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private String getCacheKey(Object k) {
        return (this.cacheKey + k).toString();
    }

    private Set<String> strkeys() {
        return RedisCacheUtils.keys(getCacheKey("*"));
    }

    public static void reloadAuthorizing(AuthorizingRealm authRealm, String userId) {
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(userId, realmName);
        subject.runAs(principals);
        authRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
    }

    public static void reloadAuthorizingAll(AuthorizingRealm authRealm, EnterpriseCacheSessionDAO sessionDAO) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        Subject subject = SecurityUtils.getSubject();
        for (Session session : sessions) {
            SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (principals != null) {
                subject.runAs(principals);
                authRealm.getAuthorizationCache().remove(subject.getPrincipals());
                subject.releaseRunAs();
            }
        }
    }

    public static void reloadAuthorizing(AuthorizingRealm authRealm, EnterpriseCacheSessionDAO sessionDAO, String userId) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        Subject subject = SecurityUtils.getSubject();
        for (Session session : sessions) {
            SimplePrincipalCollection principals = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (principals != null) {
                String principal = (String) principals.getPrimaryPrincipal();
                if (principal.equals(userId)) {
                    subject.runAs(principals);
                    authRealm.getAuthorizationCache().remove(subject.getPrincipals());
                    subject.releaseRunAs();
                }
            }
        }
    }

}
