package org.jeecf.manager.config;

import org.jeecf.manager.common.thymeleaf.SysDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * thymeleaf 配置
 * 
 * @author jianyiming
 *
 */
@Configuration
public class ThymeleafConfiguration {

    /**
     * shiro方言
     * 
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 系统方言
     * 
     * @return
     */
    @Bean
    public SysDialect sysDialect() {
        return new SysDialect(" sys dialect ");
    }

}
