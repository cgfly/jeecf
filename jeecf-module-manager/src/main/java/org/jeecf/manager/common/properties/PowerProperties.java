package org.jeecf.manager.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 权限根节点相关配置
 * 
 * @author jianyiming
 *
 */
@Data
@Configuration
public class PowerProperties {

	/**
	 * 功能名称
	 */
	@Value("${power.function.name}")
	private String functionName;
	/**
	 * 基础后缀名
	 */
	@Value("${power.suffix.base.name}")
	private String suffixBaseName;

	/**
	 * 原子edit后缀名
	 */
	@Value("${power.suffix.edit.name}")
	private String suffixEditName;

	/**
	 * 原子view后缀名
	 */
	@Value("${power.suffix.view.name}")
	private String suffixViewName;

}
