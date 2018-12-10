package org.jeecf.common.utils;

import java.text.SimpleDateFormat;
/**
 * 时间格式话工具
 * @author jianyiming
 *
 */
public class DateFormatUtils {
	
	/**
	 * 秒 格式化
	 */
	public static SimpleDateFormat getSfFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 天 格式化
	 */
	public static SimpleDateFormat getDfFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

}
