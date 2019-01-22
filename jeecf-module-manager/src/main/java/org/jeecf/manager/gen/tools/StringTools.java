package org.jeecf.manager.gen.tools;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.utils.HumpUtils;

/**
 * 字符串工具类
 * 
 * @author jianyiming
 *
 */
public class StringTools {
    /**
     * 是否为空
     * 
     * @param cs
     * @return
     */
    public boolean isEmpty(CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }

    /**
     * 是否不为空
     * 
     * @param cs
     * @return
     */
    public boolean isNotEmpty(CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }

    /**
     * 字符串 驼峰转下划线
     * 
     * @param str
     * @return
     */
    public String toLine(String str) {
        return HumpUtils.humpToLine2(str);
    }

    /**
     * 字符串 下划线转驼峰
     * 
     * @param str
     * @return
     */
    public String toHump(String str) {
        return HumpUtils.lineToHump(str);
    }

    /**
     * 分隔字符串
     * 
     * @param str            字符串
     * @param separatorChars 分隔符
     * @return
     */
    public List<String> split(String str, String separatorChars) {
        String[] result = StringUtils.split(str, separatorChars);
        return Arrays.asList(result);
    }

    /**
     * 截取字符串
     * 
     * @param str   字符串
     * @param start 开始位置
     * @return
     */
    public String substring(String str, int start) {
        return StringUtils.substring(str, start);
    }

    /**
     * 截取字符串
     * 
     * @param str   字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public String substring(String str, int start, int end) {
        return StringUtils.substring(str, start, end);
    }

    /**
     * 截取分隔符之后的字符串
     * 
     * @param str       字符串
     * @param separator 分隔符
     * @return
     */
    public String substringAfter(String str, String separator) {
        return StringUtils.substringAfter(str, separator);
    }

    /**
     * 截取分隔符最后出现的位置之后的字符串
     * 
     * @param str       字符串
     * @param separator 分隔符
     * @return
     */
    public String substringAfterLast(String str, String separator) {
        return StringUtils.substringAfterLast(str, separator);
    }

    /**
     * 截取分隔符之前的字符串
     * 
     * @param str       字符串
     * @param separator 分隔符
     * @return
     */
    public String substringBefore(String str, String separator) {
        return StringUtils.substringBefore(str, separator);
    }

    /**
     * 截取分隔符最后出现的位置之前的字符串
     * 
     * @param str       字符串
     * @param separator 分隔符
     * @return
     */
    public String substringBeforeLast(String str, String separator) {
        return StringUtils.substringBeforeLast(str, separator);
    }

    /**
     * 截取tag之间的字符串
     * 
     * @param str 字符串
     * @param tag
     * @return
     */
    public String substringBetween(String str, String tag) {
        return StringUtils.substringBetween(str, tag);
    }

    /**
     * 截取open与close之间的字符串
     * 
     * @param str   字符串
     * @param open  开始的字符串
     * @param close 结束字符串
     * @return
     */
    public String substringBetween(String str, String open, String close) {
        return StringUtils.substringBetween(str, open, close);
    }

    /**
     * 截取open与close之间的字符串 集合
     * 
     * @param str   字符串
     * @param open  开始字符串
     * @param close 结束字符串
     * @return
     */
    public List<String> substringsBetween(String str, String open, String close) {
        String[] result = StringUtils.substringsBetween(str, open, close);
        return Arrays.asList(result);
    }

}
