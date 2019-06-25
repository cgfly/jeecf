package org.jeecf.gen.model;

/**
 * gen 参数
 * 
 * @author jianyiming
 * @since1 1.0
 */
public class GenParams {
    /**
     * 名称
     */
    private String columnName;
    /**
     * 值
     */
    private String value;
    /**
     * 描述
     */
    private String descrition;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

}
