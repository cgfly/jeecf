package org.jeecf.manager.config;

import org.jeecf.osgi.utils.PluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 插件管理
 * 
 * @author jianyiming
 *
 */
@Configuration
public class PluginConfiguration {

    @Bean
    public PluginManager pluginManager() {
        return new PluginManager();
    }
}
