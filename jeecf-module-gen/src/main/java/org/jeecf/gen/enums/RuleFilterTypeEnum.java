package org.jeecf.gen.enums;

/**
 * 过滤类型枚举
 * 
 * @author jianyiming
 * @since 1.0
 */
public enum RuleFilterTypeEnum {

    /**
     * 固化
     */
    CURE(1, "cure"),
    /**
     * 参数
     */
    PARAM(2, "param"),;

    public final int code;
    public final String name;

    private RuleFilterTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static boolean contains(String name) {
        RuleFilterTypeEnum[] ruleFilterTypeEnums = RuleFilterTypeEnum.values();
        for (RuleFilterTypeEnum ruleFilterTypeEnum : ruleFilterTypeEnums) {
            if (ruleFilterTypeEnum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
