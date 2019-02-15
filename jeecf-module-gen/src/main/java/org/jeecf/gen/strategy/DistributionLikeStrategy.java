package org.jeecf.gen.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * distribution like strategy
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DistributionLikeStrategy {

    private static final char ASTERISK = '*';

    /**
     * 
     * @param data  需要匹配的数据 为json数组格式
     * @param field 需要匹配的字段
     * @param match 匹配规则
     * @return 过滤后数据
     */
    public static String handler(String data, String field, String match) {
        List<Object> result = new ArrayList<>();
        JsonNode jsonNode = JsonMapper.getJsonNode(data);
        if (jsonNode.isArray()) {
            Iterator<JsonNode> iter = jsonNode.iterator();
            while (iter.hasNext()) {
                JsonNode node = iter.next();
                String text = node.get(field).asText();
                if (StringUtils.isNotEmpty(text)) {
                    boolean isMatch = matchText(text, match);
                    if (isMatch) {
                        result.add(node);
                    }
                }
            }
        }
        return JsonMapper.toJson(result);
    }

    /**
     * 匹配算法（全量匹配）
     * 
     * @param text  要匹配的字符串
     * @param match 匹配规则字符串
     * @return 是否匹配成功
     */
    private static boolean matchText(String text, String match) {
        char[] textChars = text.toCharArray();
        char[] matchChars = match.toCharArray();
        int nextMatchar = 0;
        // 状态 0：非特殊字符 1:特殊字符 *
        int matchStatus = 0;
        // 是否匹配 默认匹配
        boolean isMatch = true;
        if (matchChars.length > textChars.length) {
            isMatch = false;
        }
        for (char textChar : textChars) {
            if (nextMatchar + 1 > matchChars.length) {
                isMatch = false;
                break;
            }
            char matchChar = matchChars[nextMatchar];
            if (matchChar == ASTERISK && nextMatchar + 1 < matchChars.length) {
                matchStatus = 1;
                nextMatchar++;
            } else if (matchChar == ASTERISK && nextMatchar + 1 == matchChars.length) {
                break;
            } else if (matchStatus == 1 && matchChar == textChar) {
                nextMatchar++;
                matchStatus = 0;
            } else if (matchStatus == 0 && matchChar == textChar) {
                nextMatchar++;
            } else if (matchStatus == 0 && matchChar != textChar) {
                isMatch = false;
                break;
            }

        }
        return isMatch;
    }
}
