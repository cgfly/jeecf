package org.jeecf.manager.engine.enums;
/**
 * where express enum 
 * @author jianyiming
 *
 */
public enum WhereExpressEnum {
	
	/**
	 * = express
	 */
	EQUALS(1,"="),
	/**
	 * < express
	 */
	LT(1,"&lt;"),
	/**
	 * > express
	 */
	GT(2,">"),
	/**
	 * <= express
	 */
	LT_EQUALS(3,"&lt;="),
	/**
	 * >= express
	 */
	GT_EQUALS(4,">="),
	;
	
	public final int code;
	public final String name;

	private WhereExpressEnum(int code, String name) {
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
