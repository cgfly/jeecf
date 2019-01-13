package org.jeecf.manager.common.thymeleaf.expression;

import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
/**
 * 数据库资源 表达式
 * @author jianyiming
 *
 */
public class Dbsources {
	
	/**
	 * 获取数据连接原名
	 * @return
	 */
	public String getName() {
		return DynamicDataSourceContextHolder.getCurrentDataSourceValue();
	}
	/**
	 * 获取数据连接原可用性
	 * @return
	 */
	public String getUsable() {
		boolean usable = DynamicDataSourceContextHolder.getCurrentDataSourceUsable();
		if(usable) {
			return EnumUtils.Usable.YES.getName();
		}
		return EnumUtils.Usable.NO.getName();
	}

}
