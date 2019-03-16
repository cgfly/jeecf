package org.jeecf.cache;

/**
 * 缓存刷新 接口类
 * 
 * @author jianyiming
 * @version 2.0
 */
public interface CacheFlush {
    /**
     * 获取缓存key
     * 
     * @param context
     * @return
     */
    public String getKey(CacheContext context);

    /**
     * 清理缓存
     * 
     * @param context
     * @param key
     */
    public void clear(CacheContext context, String key);

}
