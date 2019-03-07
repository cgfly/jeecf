package org.jeecf.common.utils;

public class PlaceholderUtils {

    private static final String placeholderPrefix = "${";

    private static final String placeholderSuffix = "}";

    public static String getText(String value) {
        value = value.trim();
        if (value.startsWith(placeholderPrefix) && value.endsWith(placeholderSuffix)) {
            return value = value.substring(2, value.length() - 1);
        }
        return null;
    }

    public static boolean isPlaceHolder(String value) {
        value = value.trim();
        if (value.startsWith(placeholderPrefix) && value.endsWith(placeholderSuffix)) {
            return true;
        }
        return false;
    }
}
