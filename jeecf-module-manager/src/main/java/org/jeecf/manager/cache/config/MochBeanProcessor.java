package org.jeecf.manager.cache.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 获取代理的实际类
 * 
 * @author jianyiming
 * @version 2.0
 */
@Component
@Slf4j
public class MochBeanProcessor implements BeanPostProcessor, ApplicationContextAware {

    public Object postProcessAfterInitialization(Object springInitedBean, String beanName) throws BeansException {
        if (springInitedBean instanceof BeanSelfAware) {
            BeanSelfAware originBean = (BeanSelfAware) springInitedBean;
            BeanSelfAware proxyBean = (BeanSelfAware) getTheSingletonObject(beanName);
            originBean.setSelf(proxyBean);
            // 用于在环境中打印, setSelf的初始过程; 便于查看log, 是否注入? 注入的是什么类?
            log.info("springInitedBean is = {},beanName = {} ", springInitedBean.getClass(), beanName);
            log.info("getTheSingletonObject is = {} ", getTheSingletonObject(beanName).getClass().getName());
            if (beanName != null && beanName.contains("want")) {
                log.info("*****origin = {}", originBean.toString());
                log.info("*****proxy= {}", proxyBean.toString());
            }
            return originBean;
        }
        return springInitedBean;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanSelfAware) {
            log.info("befor initial = {}", bean.getClass());
        }
        return bean;
    }

    private Object getTheSingletonObject(String beanName) {
        return currentCtx.getAutowireCapableBeanFactory().getBean(beanName);
    }

    private ApplicationContext currentCtx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        currentCtx = applicationContext;
    }

}
