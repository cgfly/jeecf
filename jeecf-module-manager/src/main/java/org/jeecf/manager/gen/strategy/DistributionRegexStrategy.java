package org.jeecf.manager.gen.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 分发正则匹配策略
 * @author jianyiming
 *
 */
public class DistributionRegexStrategy {
    /**
     * 处理入口
     * @param data 数据
     * @param field 匹配属性
     * @param match 匹配规则
     * @return
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
     * 匹配算法（正则匹配）
     * 
     * @param text  要匹配的字符串
     * @param match 匹配规则字符串
     * @return 是否匹配成功
     */
    private static boolean matchText(String text, String match) {
        return Pattern.matches(match,text);
    }

}
