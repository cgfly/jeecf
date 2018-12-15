package org.jeecf.manager.gen.enums;
/**
 * 规则策略类型 枚举
 * @author jianyiming
 *
 */
public enum RuleStrategyTypeEnum {
	
	/**
	 * 固化
	 */
	CURE(1,"cure"),
	/**
	 * 参数
	 */
	PARAM(2,"param"),
	;
	
	
	public final int code;
	public final String name;

	private RuleStrategyTypeEnum(int code, String name) {
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
