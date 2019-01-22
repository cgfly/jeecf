package org.jeecf.manager.gen.enums;

/**
 * 分发类型枚举
 * 
 * @author jianyiming
 *
 */
public enum DistributionTypeEnum {

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

    private DistributionTypeEnum(int code, String name) {
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
        DistributionTypeEnum[] distributionTypeEnums = DistributionTypeEnum.values();
        for (DistributionTypeEnum distributionTypeEnum : distributionTypeEnums) {
            if (distributionTypeEnum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
