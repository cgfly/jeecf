package org.jeecf.gen.resolve;

import java.util.Map;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.gen.enums.DistributionStrategyEnum;
import org.jeecf.gen.enums.RuleFilterTypeEnum;
import org.jeecf.gen.exception.DistributionStrategyNotMatchException;
import org.jeecf.gen.exception.DistributionTypeException;
import org.jeecf.gen.model.config.DistributionEntity;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 分发解析
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ConfigDistributionResolve {
    /**
     * resolve config distribution
     * 
     * @param distributionNode
     * @param paramsMap
     * @return
     */
    public static DistributionEntity process(JsonNode distributionNode, Map<String, Object> paramsMap) {
        DistributionEntity distributionEntity = new DistributionEntity();
        if (distributionNode != null) {
            distributionEntity.setActive(resolveDistributionActive(distributionNode.get("active")));
            distributionEntity.setStrategy(resolveDistributionStrategy(distributionNode.get("strategy")));
            distributionEntity.setType(resolveDistributionType(distributionNode.get("type")));
            distributionEntity.setField(resolveDistributionField(distributionNode.get("field"), distributionEntity.getType(), paramsMap));
        }
        return distributionEntity;
    }

    /**
     * 解析distribution active
     * 
     * @param activeNode
     * @return
     */
    private static boolean resolveDistributionActive(JsonNode activeNode) {
        if (activeNode != null) {
            return activeNode.asBoolean();
        }
        return false;
    }

    /**
     * resolve distribution strategy
     * 
     * @param strategyNode
     * @return
     */
    private static String resolveDistributionStrategy(JsonNode strategyNode) {
        if (strategyNode != null) {
            String strategy = StringUtils.lowerCase(strategyNode.asText());
            if (DistributionStrategyEnum.contains(strategy)) {
                return strategy;
            }
            throw new DistributionStrategyNotMatchException("distribution strategy not match ... ");
        }
        return "like";
    }

    /**
     * resolve distribution type
     * 
     * @param typeNode
     * @return
     */
    private static String resolveDistributionType(JsonNode typeNode) {
        if (typeNode != null) {
            String type = StringUtils.lowerCase(typeNode.asText());
            if (RuleFilterTypeEnum.contains(type)) {
                return type;
            }
            throw new DistributionTypeException("distribution type is error ... ");
        }
        return "default";
    }

    /**
     * resolve distribution field
     * 
     * @param fieldNode
     * @param type
     * @param paramsMap
     * @return
     */
    private static String resolveDistributionField(JsonNode fieldNode, String type, Map<String, Object> paramsMap) {
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
        return (String) paramsMap.get("distributionField");
    }

}
