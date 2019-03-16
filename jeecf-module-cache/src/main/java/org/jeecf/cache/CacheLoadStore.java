package org.jeecf.cache;

/**
 * 查询缓存 接口类
 * 
 * @author jianyiming
 * @version 2.0
 */
public interface CacheLoadStore {
    /**
     * 获取缓存key
     * 
     * @param context
     * @return
     */
    public String getKey(CacheContext context);

    /**
     * 加载缓存
     * 
     * @param context
     * @param key
     * @return
     */
    public Object load(CacheContext context, String key);

    /**
     * 存储缓存
     * 
     * @param context
     * @param key
     * @param value
     */
    public void store(CacheContext context, String key, Object value);

}
