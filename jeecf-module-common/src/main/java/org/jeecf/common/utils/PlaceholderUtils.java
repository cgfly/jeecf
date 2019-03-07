package org.jeecf.common.utils;

/**
 * 占位符 工具类
 * 
 * @author jianyiming
 *
 */
public class PlaceholderUtils {

    private static final String PLACEHOLDER_PREFIX = "${";

    private static final String PLACEHOLDER_SUFFIX = "}";

    public static String getText(String value) {
        value = value.trim();
        if (value.startsWith(PLACEHOLDER_PREFIX) && value.endsWith(PLACEHOLDER_SUFFIX)) {
            return value = value.substring(2, value.length() - 1);
        }
        return null;
    }

    public static boolean isPlaceHolder(String value) {
        value = value.trim();
        if (value.startsWith(PLACEHOLDER_PREFIX) && value.endsWith(PLACEHOLDER_SUFFIX)) {
            return true;
        }
        return false;
    }
}
