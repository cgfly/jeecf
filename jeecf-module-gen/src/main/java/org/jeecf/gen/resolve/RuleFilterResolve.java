package org.jeecf.gen.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.gen.chain.ContextConfigParams;
import org.jeecf.gen.enums.RuleFilterStrategyEnum;
import org.jeecf.gen.enums.RuleFilterTypeEnum;
import org.jeecf.gen.exception.RuleFilterStrategyException;
import org.jeecf.gen.exception.RuleFilterTypeException;
import org.jeecf.gen.exception.RuleFilterValueEmptyException;
import org.jeecf.gen.model.rule.FilterEntity;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * rule filter 解析
 * 
 * @author jianyiming
 * @since 1.0
 */
public class RuleFilterResolve {

    public static List<FilterEntity> process(JsonNode filterNodes, boolean isData, Map<String, Object> paramsMap, ContextConfigParams contextParams) {
        if (!isData) {
            return null;
        }
        List<FilterEntity> filterEntitys = new ArrayList<>();
        if (filterNodes != null) {
            if (filterNodes.isArray()) {
                Iterator<JsonNode> rulesIter = filterNodes.iterator();
                while (rulesIter.hasNext()) {
                    JsonNode ruleNode = rulesIter.next();
                    filterEntitys.add(buildFilterEntity(ruleNode, paramsMap, contextParams));
                }
            } else {
                filterEntitys.add(buildFilterEntity(filterNodes, paramsMap, contextParams));
            }
        } else {
            filterEntitys.add(new FilterEntity());
        }
        return filterEntitys;
    }

    /**
     * 构建filter实体
     * 
     * @param filterNode
     * @return
     */
    private static FilterEntity buildFilterEntity(JsonNode filterNode, Map<String, Object> paramsMap, ContextConfigParams contextParams) {
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setStrategy(resolveFilterStrategy(filterNode.get("strategy")));
        filterEntity.setType(resolveFilterType(filterNode.get("type")));
        filterEntity.setField(resolveFilterField(filterNode.get("field"), filterEntity.getType(), paramsMap));
        filterEntity.setValue(resolveFilterValue(filterNode.get("value"), filterEntity.getStrategy(), paramsMap, contextParams));
        return filterEntity;
    }

    /**
     * 解析filter 策略
     * 
     * @param strategyNode
     * @return
     */
    private static String resolveFilterStrategy(JsonNode strategyNode) {
        if (strategyNode != null) {
            String strategy = StringUtils.lowerCase(strategyNode.asText());
            if (RuleFilterStrategyEnum.contains(strategy)) {
                return strategy;
            }
            throw new RuleFilterStrategyException("rule filter strategy is error ... ");
        }
        return "namespace";
    }

    /**
     * 解析filter 类型
     * 
     * @param strategyNode
     * @return
     */
    private static String resolveFilterType(JsonNode typeNode) {
        if (typeNode != null) {
            String type = StringUtils.lowerCase(typeNode.asText());
            if (RuleFilterTypeEnum.contains(type)) {
                return type;
            }
            throw new RuleFilterTypeException("rule filter type is error ... ");
        }
        return "default";
    }

    /**
     * 解析filter属性
     * 
     * @param fieldNode
     * @param type
     * @return
     */
    private static String resolveFilterField(JsonNode fieldNode, String type, Map<String, Object> paramsMap) {
        if (fieldNode != null) {
            if (type.equals(RuleFilterTypeEnum.CURE.getName())) {
                return fieldNode.asText();
            } else if (type.equals(RuleFilterTypeEnum.PARAM.getName())) {
                return (String) paramsMap.get(fieldNode.asText());
            } else if (type.equals(RuleFilterTypeEnum.DEFAULT.getName())) {
                Object obj = paramsMap.get(fieldNode.asText());
                if (obj == null) {
                    return fieldNode.asText();
                } else {
                    return (String) obj;
                }
            }
        }
        return (String) paramsMap.get("filterField");
    }

    /**
     * 解析filter值
     * 
     * @param valueNode
     * @param strategy
     * @return
     */
    private static String resolveFilterValue(JsonNode valueNode, String strategy, Map<String, Object> paramsMap, ContextConfigParams contextParams) {
        if (strategy.equals(RuleFilterStrategyEnum.VALUE.getName()) && valueNode == null) {
            throw new RuleFilterValueEmptyException("rule filter type is error ... ");
        }
        if (valueNode != null) {
            if (strategy.equals(RuleFilterStrategyEnum.NAMESPACE.getName())) {
                return contextParams.getNamespaceId();
            } else if (strategy.equals(RuleFilterStrategyEnum.USER.getName())) {
                return contextParams.getUserId();
            } else if (strategy.equals(RuleFilterStrategyEnum.VALUE.getName())) {
                return (String) paramsMap.get(valueNode.asText());
            }
        }
        return contextParams.getNamespaceId();
    }

}
