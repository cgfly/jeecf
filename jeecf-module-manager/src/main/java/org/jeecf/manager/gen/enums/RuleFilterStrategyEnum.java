package org.jeecf.manager.gen.enums;
/**
 * 过滤策略枚举
 * @author jianyiming
 *
 */
public enum RuleFilterStrategyEnum {
	/**
	 * 命名空间
	 */
	NAMESPACE(1,"namespace"),
	/**
	 * 用户
	 */
	USER(2,"user"),
	
	/**
	 * 值
	 */
	VALUE(3,"value"),
	;
	
	public final int code;
	public final String name;

	private RuleFilterStrategyEnum(int code, String name) {
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
