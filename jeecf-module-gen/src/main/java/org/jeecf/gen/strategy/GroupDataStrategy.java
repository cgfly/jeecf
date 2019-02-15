package org.jeecf.gen.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.utils.EncodeUtils;
import org.jeecf.gen.exception.DataGroupEmptyException;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * group data 策略
 * 
 * @author jianyiming
 * @since 1.0
 */
public class GroupDataStrategy {

    public static List<Map<String, Object>> handler(String data, String... groups) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (groups.length > 0) {
            JsonNode jsonNode = JsonMapper.getJsonNode(data);
            if (jsonNode.isArray()) {
                Map<String, List<JsonNode>> countMap = new HashMap<>(10);
                Iterator<JsonNode> iter = jsonNode.iterator();
                while (iter.hasNext()) {
                    String globel = "";
                    JsonNode node = iter.next();
                    for (String group : groups) {
                        JsonNode groupNode = node.get(group);
                        if (groupNode == null) {
                            return result;
                        }
                        globel += EncodeUtils.encodeBase64(groupNode.asText()) + SplitCharEnum.UNDERLINE.getName();
                    }
                    globel = StringUtils.substringBeforeLast(globel, SplitCharEnum.UNDERLINE.getName());
                    List<JsonNode> value = countMap.get(globel);
                    if (value == null) {
                        value = new ArrayList<>();
                    }
                    value.add(node);
                    countMap.put(globel, value);
                }
                for (Map.Entry<String, List<JsonNode>> entry : countMap.entrySet()) {
                    Map<String, Object> resultMap = new HashMap<>(10);
                    String[] keys = entry.getKey().split(SplitCharEnum.UNDERLINE.getName());
                    for (int i = 0; i < keys.length; i++) {
                        if (i == 0) {
                            resultMap.put("_name", EncodeUtils.decodeBase64String(keys[i]));
                        }
                        resultMap.put(groups[i], EncodeUtils.decodeBase64String(keys[i]));
                    }
                    resultMap.put("_count", entry.getValue().size());
                    resultMap.put("_nodes", JsonMapper.toJson(entry.getValue()));
                    result.add(resultMap);
                }
            }
        }
        if (CollectionUtils.isEmpty(result)) {
            throw new DataGroupEmptyException("data group data is empty ... ");
        }
        return result;
    }

}
