package org.jeecf.common.lang;

import java.util.HashSet;
import java.util.Set;

/**
 * 扩展 apache.lang StringUtils类
 * 
 * @author GloryJian
 *
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String DEFAULT_SPLIT = ",";

    public static Set<String> toSet(String value) {
        return toSet(value, DEFAULT_SPLIT);
    }

    public static Set<String> toSet(String value, String split) {
        Set<String> result = new HashSet<>();
        if (StringUtils.isNotEmpty(value)) {
            String[] values = value.split(split);
            for (String v : values) {
                result.add(v);
            }
        }
        return result;
    }

}
