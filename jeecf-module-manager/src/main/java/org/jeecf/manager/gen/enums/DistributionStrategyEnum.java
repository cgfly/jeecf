package org.jeecf.manager.gen.enums;

/**
 * 分发策略枚举
 * 
 * @author jianyiming
 *
 */
public enum DistributionStrategyEnum {

    /**
     * like
     */
    LIKE(1, "like"),
    /**
     * regex
     */
    REGEX(1, "regex"),
    ;

    public final int code;
    public final String name;

    private DistributionStrategyEnum(int code, String name) {
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
        DistributionStrategyEnum[] distributionStrategyEnums = DistributionStrategyEnum.values();
        for (DistributionStrategyEnum distributionStrategyEnum : distributionStrategyEnums) {
            if (distributionStrategyEnum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
