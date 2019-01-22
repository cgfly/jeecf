package org.jeecf.manager.engine.enums;

/**
 * 排序枚举
 * 
 * @author jianyiming
 *
 */
public enum SortModeEnum {

    /**
     * ASC
     */
    ASC(1, "ASC"),

    /**
     * DESC
     */
    DESC(2, "DESC"),;

    public final int code;
    public final String name;

    private SortModeEnum(int code, String name) {
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
