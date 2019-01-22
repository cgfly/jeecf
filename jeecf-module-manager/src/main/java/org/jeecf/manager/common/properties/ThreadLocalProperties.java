package org.jeecf.manager.common.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

/**
 * ThreadLocal 参数存储
 * 
 * @author jianyiming
 *
 */
@Configuration
public class ThreadLocalProperties {

    private ThreadLocal<Map<String, String>> local = new ThreadLocal<>();

    /**
     * 设置参数
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Map<String, String> localMap = local.get();
        if (localMap == null) {
            localMap = new HashMap<String, String>(10);
            local.set(localMap);
        }
        localMap.put(key, value);
    }

    /**
     * 获取参数
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        Map<String, String> localMap = local.get();
        if (localMap != null) {
            return localMap.get(key);
        }
        return null;
    }

    /**
     * 回收
     */
    public void remove() {
        local.remove();
    }

}
