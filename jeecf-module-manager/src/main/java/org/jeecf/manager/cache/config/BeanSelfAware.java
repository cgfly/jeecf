package org.jeecf.manager.cache.config;

/**
 * 获取代理 织入类
 * 
 * @author jianyiming
 * @version 2.0
 */
public interface BeanSelfAware {

    void setSelf(Object proxyBean);

}
