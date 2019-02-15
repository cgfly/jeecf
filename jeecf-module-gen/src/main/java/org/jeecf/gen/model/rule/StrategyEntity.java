package org.jeecf.gen.model.rule;

/**
 * 策略实体
 * 
 * @author jianyiming
 * @since 1.0
 */
public class StrategyEntity {

    /**
     * 策略名称
     */
    private String name;
    /**
     * 策略类型
     */
    private String type = "param";
    /**
     * 策略属性
     */
    private String field = "strategyField";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
