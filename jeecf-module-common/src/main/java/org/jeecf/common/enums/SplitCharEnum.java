package org.jeecf.common.enums;
/**
 * 分割符枚举
 * @author jianyiming
 *
 */
public enum SplitCharEnum {
    /**
     * 分割符 ,
     */
    COMMA(1,","),
    /**
     * 分割符 *
     */
    ASTERISK(2,"*"),
    /**
     * 分割符 _
     */
    UNDERLINE(3,"_"),
    /**
     * 分割符 .
     */
    DOT(4,"."),
    /**
     * 分割符 ;
     */
    SEMICOLON(5,";"),
    /**
     * 分割符 :
     */
    COLON(6,":"),
    /**
     * 斜杠 /
     */
    SLASH(7,"/")
    ;
	public final int code;
	public final String name;

	private SplitCharEnum(int code, String name) {
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
