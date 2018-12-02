package org.jeecf.manager.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 代码生成字典 属性
 * @author jianyiming
 *
 */
@Data
@Configuration
public class DictProperties {
	/**
	 * java 功能版本
	 */
	@Value("${gen.dict.java.function.version}")
	private String javaFunctionVersion;
	/**
	 * java 功能作者
	 */
	@Value("${gen.dict.java.function.author}")
	private String javaFunctionAuthor;
	/**
	 * java 包名
	 */
	@Value("${gen.dict.java.packageName}")
	private String javaPackageName;
	
	/**
	 * java 功能名称
	 */
	@Value("${gen.dict.java.function.name}")
	private String javaFunctionName;

}
