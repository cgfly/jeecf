package org.jeecf.manager.engine.enums;
/**
 * 字段是否为空 枚举
 * @author jianyiming
 *
 */
public enum NullModelEnum {
	
	/**
	 * NULL
	 */
	NULL(1,"NULL"),
	
	/**
	 * NOT NULL
	 */
	NOT_NULL(2,"NOT NULL"),
	;
	
	public final int code;
	public final String name;

	private NullModelEnum(int code, String name) {
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
