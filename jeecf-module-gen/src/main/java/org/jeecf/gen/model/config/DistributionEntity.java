package org.jeecf.gen.model.config;

/**
 * 分发实体
 * 
 * @author jianyiming
 * @since 1.0
 */
public class DistributionEntity {

    /**
     * 是否生效
     */
    private boolean active = false;
    /**
     * 分发规则策略
     */
    private String strategy = "like";

    /**
     * 分发规则类型
     */
    private String type = "param";
    /**
     * 分发规则属性
     */
    private String field = "distributionField";

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
