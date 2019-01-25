package org.jeecf.engine.mysql.model;

/**
 * 抽象表
 * 
 * @author jianyiming
 *
 */
public class AbstractTable {

    protected AbstractTable() {
    };

    /**
     * 表名称
     */
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
