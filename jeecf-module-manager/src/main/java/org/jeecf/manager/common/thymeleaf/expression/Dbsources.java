package org.jeecf.manager.common.thymeleaf.expression;

import org.jeecf.manager.config.DynamicDataSourceContextHolder;
/**
 * 数据库资源 表达式
 * @author jianyiming
 *
 */
public class Dbsources {
	
	/**
	 * 获取命名空间名
	 * @return
	 */
	public String getName() {
		return DynamicDataSourceContextHolder.getCurrentDataSourceValue();
	}

}
