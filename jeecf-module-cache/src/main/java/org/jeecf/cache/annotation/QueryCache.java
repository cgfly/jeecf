package org.jeecf.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jeecf.cache.enums.TypeEnum;

/**
 * 查询缓存
 * 
 * @author jianyiming
 * @version 2.0
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface QueryCache {

    /**
     * 缓存方法名称
     */
    public String name() default "";

    /**
     * 返回结果类
     * 
     * @return
     */
    public Class<? extends Object> returnClass() default Object.class;

    /**
     * 返回类型标识
     * 
     * @return
     */
    public TypeEnum type() default TypeEnum.GET;

}
