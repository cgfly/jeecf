package org.jeecf.manager.gen.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.utils.EncodeUtils;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 实体数据规则
 * 
 * @author jianyiming
 *
 */
public class ObjectRule {

	public String toJsonString(Object data) {
		return JsonMapper.toJson(data);
	}

	public String groups(String data, String... groups) {
		if (groups.length > 0) {
			JsonNode jsonNode = JsonMapper.getJsonNode(data);
			if (jsonNode.isArray()) {
				List<Map<String, Object>> result = new ArrayList<>();
				Map<String, List<JsonNode>> countMap = new HashMap<>();
				Iterator<JsonNode> iter = jsonNode.iterator();
				while (iter.hasNext()) {
					String globel = "";
					JsonNode node = iter.next();
					for (String group : groups) {
						globel += EncodeUtils.encodeBase64(node.get(group).asText())
								+ SplitCharEnum.UNDERLINE.getName();
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
					Map<String, Object> resultMap = new HashMap<>();
					String[] keys = entry.getKey().split(SplitCharEnum.UNDERLINE.getName());
					for (int i = 0; i < keys.length; i++) {
						resultMap.put(groups[i], EncodeUtils.decodeBase64String(keys[i]));
					}
					resultMap.put("_count", entry.getValue().size());
					resultMap.put("_nodes", entry.getValue());
					result.add(resultMap);
				}
				return JsonMapper.toJson(result);
			}
		}
		return data;
	}

}
