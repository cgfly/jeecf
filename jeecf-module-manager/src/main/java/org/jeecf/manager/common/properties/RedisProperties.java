package org.jeecf.manager.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * redis 属性
 * @author jianyiming
 *
 */
@Component  
@ConfigurationProperties(prefix="redis")
@Data
public class RedisProperties {
	
	private String host;
	
	private Integer port;
	
	private Integer maxTotal;
	
	private Integer maxIdle;
	
	private Long maxWaitMillis;
	
	private Boolean testWhileIdle;
	
	private Long timeBetweenEvictionRunsMillis;
	
	private Long minEvictableIdleTimeMillis;
	
}
