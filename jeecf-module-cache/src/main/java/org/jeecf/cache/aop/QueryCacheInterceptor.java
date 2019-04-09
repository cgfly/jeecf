package org.jeecf.cache.aop;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jeecf.cache.CacheContext;
import org.jeecf.cache.CacheLoadStore;
import org.jeecf.cache.annotation.Cache;
import org.jeecf.cache.annotation.QueryCache;
import org.jeecf.cache.exception.CacheNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 查询缓存 切入类
 * 
 * @author jianyiming
 * @version 2.0
 */
@Aspect
@Order(-10)
@Component
public class QueryCacheInterceptor {

    @Around("@annotation(queryCache)")
    public Object aroundMethod(ProceedingJoinPoint pjp, QueryCache queryCache) throws Throwable {
        Class<? extends Object> cls = pjp.getTarget().getClass();
        Annotation[] annotations = cls.getAnnotations();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Class<? extends Object> returnClass = queryCache.returnClass();
        Object[] args = pjp.getArgs();
        if (StringUtils.isNotEmpty(queryCache.name())) {
            methodName = queryCache.name();
        }
        if (annotations != null && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Cache.class) {
                    Cache cache = (Cache) annotation;
                    if (cache.open()) {
                        Integer timeout = cache.timeout();
                        CacheLoadStore cacheLoadStore = cache.cacheLoadStore().newInstance();
                        if (StringUtils.isNotEmpty(cache.name())) {
                            className = cache.name();
                        }
                        CacheContext context = new CacheContext();
                        context.setClassName(className);
                        context.setMethodName(methodName);
                        context.setArgs(args);
                        context.setClazz(cls);
                        context.setType(queryCache.type());
                        context.setReturnClass(returnClass);
                        context.setTimeout(timeout);
                        String key = cacheLoadStore.getKey(context);
                        Object obj = cacheLoadStore.load(context, key);
                        if (obj == null) {
                            obj = pjp.proceed();
                            cacheLoadStore.store(context, key, obj);
                        }
                        return obj;
                    }
                    return pjp.proceed();
                }
            }
        }
        throw new CacheNotFoundException();
    }

}
