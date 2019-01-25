package org.jeecf.engine.mysql.enums;

/**
 * 索引方法枚举
 * 
 * @author jianyiming
 *
 */
public enum IndexWayEnum {

    /**
     * BTREE
     */
    BTREE(1, "BTREE"),
    /**
     * HASH
     */
    HASH(2, "HASH"),;

    public final int code;
    public final String name;

    private IndexWayEnum(int code, String name) {
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
