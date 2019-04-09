package org.jeecf.cache.aop;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.jeecf.cache.CacheContext;
import org.jeecf.cache.CacheFlush;
import org.jeecf.cache.annotation.Cache;
import org.jeecf.cache.annotation.FlushCache;
import org.jeecf.cache.exception.CacheNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 刷新缓存 切入类
 * 
 * @author jianyiming
 * @version 2.0
 */
@Aspect
@Order(-10)
@Component
public class FlushCacheInterceptor {

    @After("@annotation(flushCache)")
    public void afterMethod(JoinPoint pjp, FlushCache flushCache) throws Throwable {
        Class<? extends Object> cls = pjp.getTarget().getClass();
        Annotation[] annotations = cls.getAnnotations();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        if (StringUtils.isNotEmpty(flushCache.name())) {
            methodName = flushCache.name();
        }
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Cache.class) {
                    Cache cache = (Cache) annotation;
                    if (cache.open()) {
                        Integer timeout = cache.timeout();
                        CacheFlush cacheFlush = cache.cacheFlush().newInstance();
                        if (StringUtils.isNotEmpty(cache.name())) {
                            className = cache.name();
                        }
                        CacheContext context = new CacheContext();
                        context.setClassName(className);
                        context.setMethodName(methodName);
                        context.setArgs(args);
                        context.setClazz(cls);
                        context.setTimeout(timeout);
                        String key = cacheFlush.getKey(context);
                        if (StringUtils.isNotEmpty(key)) {
                            cacheFlush.clear(context, key);
                        }
                    }
                    return;
                }
            }
        }
        throw new CacheNotFoundException();
    }

}
