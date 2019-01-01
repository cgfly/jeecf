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
	/**
	 * 树策略
	 */
	TREE(3,"tree_data"),
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
	
	public static boolean contains(String name) {
		RuleStrategyNameEnum[] ruleStrategyNameEnums = RuleStrategyNameEnum.values();
		for(RuleStrategyNameEnum ruleStrategyNameEnum : ruleStrategyNameEnums) {
			if(ruleStrategyNameEnum.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
