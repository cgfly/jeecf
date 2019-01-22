package org.jeecf.manager.gen.strategy;

import java.util.Iterator;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.enums.BusinessErrorEnum;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * tree data 策略
 * 
 * @author jianyiming
 *
 */
public class TreeDataStrategy {

    private static final String ID = "id";

    private static final String PID = "parent_id";

    private static final String CHILDS = "_childs";

    public static String handler(String data) {
        JsonNode dataNodes = JsonMapper.getJsonNode(data);
        ArrayNode resultNode = JsonNodeFactory.instance.arrayNode();
        ObjectNode objNode = JsonNodeFactory.instance.objectNode();
        if (dataNodes.isArray()) {
            Iterator<JsonNode> iterNode = dataNodes.iterator();
            while (iterNode.hasNext()) {
                JsonNode dataNode = iterNode.next();
                objNode.set(dataNode.get(ID).asText(), dataNode);
            }
            while (iterNode.hasNext()) {
                JsonNode dataNode = iterNode.next();
                ObjectNode parentNode = (ObjectNode) objNode.get(dataNode.get(PID).asText());
                if (parentNode != null) {
                    ArrayNode childNodes = (ArrayNode) parentNode.get(CHILDS);
                    if (childNodes == null) {
                        childNodes = JsonNodeFactory.instance.arrayNode();
                    }
                    childNodes.add(dataNode);
                    parentNode.set(CHILDS, childNodes);
                } else {
                    resultNode.add(dataNode);
                }
            }
            return JsonMapper.toJson(resultNode);
        }
        if (StringUtils.isEmpty(data)) {
            throw new BusinessException(BusinessErrorEnum.RULE_DATA_TREE_ERROR);
        }
        return data;
    }

}
