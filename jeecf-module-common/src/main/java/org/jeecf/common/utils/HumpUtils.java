package org.jeecf.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线与驼峰 互转工具类
 * 
 * @author jianyiming
 */
public class HumpUtils {

    private static Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     * 
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     * 
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 驼峰转下划线,效率比上面高
     * 
     * @param str
     * @return
     */
    public static String humpToLine2(String str) {
        StringBuffer sb = new StringBuffer();
        char[] cs = str.toCharArray();
        sb.append(cs[0]);
        for (int i = 1; i < cs.length; i++) {
            if (Character.isUpperCase(cs[i]) && Character.isLowerCase(cs[i - 1])) {
                sb.append("_" + cs[i]);
                continue;
            } else if (i >= 2 && Character.isLowerCase(cs[i]) && Character.isUpperCase(cs[i - 1])
                    && Character.isUpperCase(cs[i - 2])) {
                sb.append("_" + cs[i]);
                continue;
            }
            sb.append(cs[i]);
        }
        return sb.toString();
    }

}
