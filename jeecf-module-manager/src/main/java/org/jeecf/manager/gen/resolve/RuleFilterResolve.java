package org.jeecf.manager.gen.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.gen.enums.RuleFilterStrategyEnum;
import org.jeecf.manager.gen.enums.RuleFilterTypeEnum;
import org.jeecf.manager.gen.model.rule.FilterEntity;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * rule filter 解析
 * @author jianyiming
 *
 */
public class RuleFilterResolve  {
	
	public List<FilterEntity> handler(JsonNode filterNodes,boolean isData,Map<String, Object> paramsMap) {
		if (!isData) {
			return null;
		}
		List<FilterEntity> filterEntitys = new ArrayList<>();
		if (filterNodes != null) {
			if (filterNodes.isArray()) {
				Iterator<JsonNode> rulesIter = filterNodes.iterator();
				while (rulesIter.hasNext()) {
					JsonNode ruleNode = rulesIter.next();
					filterEntitys.add(this.buildFilterEntity(ruleNode,paramsMap));
				}
			} else {
				filterEntitys.add(this.buildFilterEntity(filterNodes,paramsMap));
			}
		} else {
			filterEntitys.add(new FilterEntity());
		}
		return filterEntitys;
	}

    /**
     * 构建filter实体
     * @param filterNode
     * @return
     */
	private FilterEntity buildFilterEntity(JsonNode filterNode,Map<String, Object> paramsMap) {
		FilterEntity filterEntity = new FilterEntity();
		filterEntity.setStrategy(resolveFilterStrategy(filterNode.get("strategy")));
		filterEntity.setType(resolveFilterType(filterNode.get("type")));
		filterEntity.setField(resolveFilterField(filterNode.get("field"), filterEntity.getType(),paramsMap));
		filterEntity.setValue(resolveFilterValue(filterNode.get("value"), filterEntity.getStrategy(),paramsMap));
		return filterEntity;
	}
    /**
     * 解析filter 策略
     * @param strategyNode
     * @return
     */
	private String resolveFilterStrategy(JsonNode strategyNode) {
		if (strategyNode != null) {
			return StringUtils.lowerCase(strategyNode.asText());
		}
		return "namespace";
	}
    /**
     * 解析filter 类型
     * @param strategyNode
     * @return
     */
	private String resolveFilterType(JsonNode typeNode) {
		if (typeNode != null) {
			return StringUtils.lowerCase(typeNode.asText());
		}
		return "param";
	}
    /**
     * 解析filter属性
     * @param fieldNode
     * @param type
     * @return
     */
	private String resolveFilterField(JsonNode fieldNode, String type,Map<String, Object> paramsMap) {
		if (fieldNode != null) {
			if (type.equals(RuleFilterTypeEnum.CURE.getName())) {
				return fieldNode.asText();
			} else if (type.equals(RuleFilterTypeEnum.PARAM.getName())) {
				return (String) paramsMap.get(fieldNode.asText());
			}
			throw new BusinessException(BusinessErrorEnum.RULE_FILTER_TYPE_ERROR);
		}
		return (String) paramsMap.get("filterField");
	}
    /**
     * 解析filter值
     * @param valueNode
     * @param strategy
     * @return
     */
	private String resolveFilterValue(JsonNode valueNode, String strategy,Map<String, Object> paramsMap) {
		if (strategy.equals(RuleFilterStrategyEnum.VALUE.getName()) && valueNode == null) {
			throw new BusinessException(BusinessErrorEnum.RULE_FILTER_VALUE_EMPTY);
		}
		if (valueNode != null) {
			if (strategy.equals(RuleFilterStrategyEnum.NAMESPACE.getName())) {
				return String.valueOf(NamespaceUtils.getNamespaceId());
			} else if (strategy.equals(RuleFilterStrategyEnum.USER.getName())) {
				return UserUtils.getCurrentUserId();
			} else if (strategy.equals(RuleFilterStrategyEnum.VALUE.getName())) {
				return (String) paramsMap.get(valueNode.asText());
			}
		}
		return String.valueOf(NamespaceUtils.getNamespaceId());
	}

}
