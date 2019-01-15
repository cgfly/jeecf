package org.jeecf.manager;

import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.config.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 * @author jianyiming
 *
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfiguration.class })
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
