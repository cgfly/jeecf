package org.jeecf.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jeecf.cache.CacheFlush;
import org.jeecf.cache.CacheLoadStore;

/**
 * 缓存
 * 
 * @author jianyiming
 * @version 2.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cache {
    /**
     * 缓存类名称
     */
    public String name() default "";

    /**
     * 缓存超时时间
     * 
     * @return
     */
    public int timeout() default 60 * 60;

    /**
     * 用于查询缓存实现
     * 
     * @return
     */
    public Class<? extends CacheLoadStore> cacheLoadStore();

    /**
     * 
     * @return
     */
    public Class<? extends CacheFlush> cacheFlush();

}
