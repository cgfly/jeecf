package org.jeecf.manager.engine.enums;

/**
 * 索引类型枚举
 * 
 * @author jianyiming
 *
 */
public enum IndexTypeEnum {

    /**
     * NORMAL
     */
    NORMAL(1, "NORMAL"),
    /**
     * UNIQUE
     */
    UNIQUE(2, "UNIQUE"),;

    public final int code;
    public final String name;

    private IndexTypeEnum(int code, String name) {
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
