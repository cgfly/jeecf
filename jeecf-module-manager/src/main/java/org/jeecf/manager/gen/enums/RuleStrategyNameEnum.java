package org.jeecf.manager.gen.enums;
/**
 * 规则策略名称枚举
 * @author jianyiming
 *
 */
public enum RuleStrategyNameEnum {
	
	/**
	 * 分表
	 */
	MANY(1,"many_table"),
	/**
	 * 组数据
	 */
	GROUP(2,"group_data"),
	;
	
	public final int code;
	public final String name;

	private RuleStrategyNameEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

}
