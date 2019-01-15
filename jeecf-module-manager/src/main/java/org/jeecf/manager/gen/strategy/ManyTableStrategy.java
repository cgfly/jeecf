package org.jeecf.manager.gen.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.gen.builder.TableBuilder;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * many table 策略
 * 
 * @author jianyiming
 *
 */
public class ManyTableStrategy {

	public List<Object> handler(String data, String field, TableBuilder builder) {
		List<Object> tables = new ArrayList<>();
		JsonNode dataNodes = JsonMapper.getJsonNode(data);
		if (dataNodes.isArray()) {
			Iterator<JsonNode> iterNode = dataNodes.iterator();
			while (iterNode.hasNext()) {
				JsonNode fieldNode = iterNode.next().get(field);
				if (fieldNode != null) {
					tables.add(builder.build(fieldNode.asText()));
				}
			}
		}
		return tables;
	}

}
