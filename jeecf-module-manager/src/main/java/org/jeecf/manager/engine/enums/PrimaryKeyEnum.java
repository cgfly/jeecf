package org.jeecf.manager.engine.enums;

/**
 * 主键枚举
 * 
 * @author jianyiming
 *
 */
public enum PrimaryKeyEnum {

    /**
     * 自增主键
     */
    AUTO(1, "AUTO_INCREMENT PRIMARY KEY"),

    /**
     * 非自增主键
     */
    NOT_AUTO(2, "PRIMARY KEY"),;

    public final int code;
    public final String name;

    private PrimaryKeyEnum(int code, String name) {
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
