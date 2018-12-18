package org.jeecf.manager.gen.model.rule;

import org.jeecf.manager.common.utils.NamespaceUtils;
/**
 * 规则过滤实体
 * @author jianyiming
 *
 */
public class FilterEntity {

	/**
	 * 过滤规则策略
	 */
	private String strategy = "namespace";

	/**
	 * 过滤规则类型
	 */
	private String type = "param";
	/**
	 * 过滤规则属性
	 */
	private String field = "filterField";

	/**
	 * 过滤规则值
	 */
	private String value = String.valueOf(NamespaceUtils.getNamespaceId());

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
