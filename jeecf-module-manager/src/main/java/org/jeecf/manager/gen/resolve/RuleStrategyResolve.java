package org.jeecf.manager.gen.resolve;

import java.util.Map;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.gen.enums.RuleStrategyTypeEnum;
import org.jeecf.manager.gen.model.rule.StrategyEntity;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * rule Strategy 解析
 * @author jianyiming
 *
 */
public class RuleStrategyResolve {
	
	public StrategyEntity handler(JsonNode strategyNodes,boolean isData,Map<String, Object> paramsMap) {
		if (!isData || strategyNodes == null) {
			return null;
		}
	    StrategyEntity strategyEntity = new StrategyEntity();
	    strategyEntity.setName(resolveName(strategyNodes.get("name")));
	    strategyEntity.setType(resolveType(strategyNodes.get("type")));
	    strategyEntity.setField(resolveField(strategyNodes.get("field"),strategyEntity.getType(),paramsMap));
	    return strategyEntity;
	}
	
	/**
	 * 解析名字
	 * @param strategyNode
	 * @return
	 */
	private String resolveName(JsonNode strategyNode) {
		if (strategyNode != null) {
			return StringUtils.lowerCase(strategyNode.asText());
		}
		return null;
	}

	/**
	 * 解析类型
	 * @param typeNode
	 * @return
	 */
	private String resolveType(JsonNode typeNode) {
		if (typeNode != null) {
			return StringUtils.lowerCase(typeNode.asText());
		}
		return "param";
	}

	/**
	 * 解析属性
	 * @param fieldNode
	 * @param type
	 * @param paramsMap
	 * @return
	 */
	private String resolveField(JsonNode fieldNode, String type,Map<String, Object> paramsMap) {
		if (fieldNode != null) {
			if (type.equals(RuleStrategyTypeEnum.CURE.getName())) {
				return fieldNode.asText();
			} else if (type.equals(RuleStrategyTypeEnum.PARAM.getName())) {
				return (String) paramsMap.get(fieldNode.asText());
			}
			throw new BusinessException(BusinessErrorEnum.RULE_TABLE_COLUMN_TYPE_ERROR);
		}
		return (String) paramsMap.get("strategyField");
	}

}
