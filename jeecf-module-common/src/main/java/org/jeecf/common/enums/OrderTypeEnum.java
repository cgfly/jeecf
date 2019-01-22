package org.jeecf.common.enums;

/**
 * 排序类型枚举
 * 
 * @author jianyiming
 *
 */
public enum OrderTypeEnum {
    /**
     * 降序排序
     */
    DESC("desc", "降序"),
    /**
     * 升序排序
     */
    ASC("asc", "升序"),;

    public final String code;
    public final String name;

    private OrderTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.name;
    }

}
