package org.jeecf.common.lang;

import java.util.Arrays;
import java.util.List;

/**
 * 扩展  apache.lang StringUtils类
 * 
 * @author GloryJian
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
	private static final String DEFAULT_SPLIT = ",";
    
    public static List<String> toList(String value) {
    	return toList(value,DEFAULT_SPLIT);
    }
    
    public static List<String> toList(String value,String split) {
    	if(StringUtils.isNotEmpty(value)) {
    		String[] values =value.split(split);
    		return Arrays.asList(values);
    	}
    	return null;
    }
}
