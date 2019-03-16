package org.jeecf.manager.cache;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.cache.CacheContext;
import org.jeecf.cache.CacheFlush;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.manager.common.utils.RedisCacheUtils;

/**
 * 刷新缓存的实现类
 * 
 * @author jianyiming
 * @version 2.0
 */
public class ClassCacheFlush implements CacheFlush {

    @Override
    public String getKey(CacheContext context) {
        String className = context.getClassName();
        StringBuilder key = new StringBuilder(className + SplitCharEnum.COLON.getName() + SplitCharEnum.ASTERISK.getName());
        return key.toString();
    }

    @Override
    public void clear(CacheContext context, String key) {
        Set<String> keys = RedisCacheUtils.keys(key);
        if (CollectionUtils.isNotEmpty(keys)) {
            keys.forEach(k -> {
                RedisCacheUtils.delCache(k);
            });
        }

    }

}
