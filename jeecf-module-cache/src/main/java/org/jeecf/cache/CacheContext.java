package org.jeecf.cache;

import org.jeecf.cache.enums.TypeEnum;

/**
 * 缓存上下文
 * 
 * @author jianyiming
 * @version 2.0
 */
public class CacheContext {
    /**
     * 缓存类
     */
    private Class<? extends Object> clazz;
    /**
     * 缓存类名
     */
    private String className;
    /**
     * 缓存方法名
     */
    private String methodName;
    /**
     * 缓存超时时间
     */
    private Integer timeout;
    /**
     * 缓存方法参数
     */
    private Object[] args;
    /**
     * 缓存策略类型
     */
    private TypeEnum type;
    /**
     * 缓存返回类
     */
    private Class<? extends Object> returnClass;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Class<? extends Object> getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(Class<? extends Object> returnClass) {
        this.returnClass = returnClass;
    }

}
