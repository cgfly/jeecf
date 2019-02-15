package org.jeecf.gen.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.gen.exception.TableManyEmptyException;
import org.jeecf.gen.utils.TableHook;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * many table 策略
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ManyTableStrategy {

    public static List<Object> handler(String data, String field, TableHook builder) {
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
        if (CollectionUtils.isEmpty(tables)) {
            throw new TableManyEmptyException("table many data is empty... ");
        }
        return tables;
    }

}
