package org.jeecf.manager.engine.enums;

/**
 * where connector enum 
 * @author jianyiming
 *
 */
public enum WhereConnectorEnum {
	
	/**
	 * and Connector
	 */
	AND(1,"and"),
	/**
	 * or Connector
	 */
	OR(2,"or"),
	;
	
	public final int code;
	public final String name;

	private WhereConnectorEnum(int code, String name) {
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
