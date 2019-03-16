package org.jeecf.manager;

import org.jeecf.cache.EnableCache;
import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.config.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 * 
 * @author jianyiming
 *
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCache
@Import({ DataSourceConfiguration.class })
@PropertySource(value = { "classpath:properties/permission.properties" }, encoding = "UTF-8")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            Class.forName(JdbcUtils.DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
