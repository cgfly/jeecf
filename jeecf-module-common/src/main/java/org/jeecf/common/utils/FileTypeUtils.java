package org.jeecf.common.utils;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;

/**
 * 
 * @author jianyiming
 *
 */
public class FileTypeUtils {
	
   
	/**
	 * 文件名称是否为指定类型
	 * @param type 文件类型
	 * @param name 文件名
	 * @return
	 */
	public static boolean isType(String type,String name) {
		String  suffix = getSuffix(name);
		if(suffix.equals(type)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取文件名称类型后缀
	 * @param name
	 * @return
	 */
	public static String getSuffix(String name) {
	    if(name.contains(SplitCharEnum.DOT.getName())) {
	       return StringUtils.substringAfterLast(name, SplitCharEnum.DOT.getName());
	    }
		return null;
	}
	
}
