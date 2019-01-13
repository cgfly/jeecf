package org.jeecf.manager.engine.enums;

/**
 * 默认值 枚举
 * @author jianyiming
 *
 */
public enum DefaultValueEnum {
	
	/**
	 * empty_string
	 */
	EMPTY_STRING(1,"''"),
	/**
	 * 0
	 */
	ZERO(2,"0"),
	/**
	 * CURRENT_TIMESTAMP
	 */
	CURRENT_TIMESTAMP(3,"CURRENT_TIMESTAMP"),
	;
	
	public final int code;
	public final String name;

	private DefaultValueEnum(int code, String name) {
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
