package org.jeecf.manager.engine.model;

/**
 * 
 * @author jianyiming
 *
 */
public class AbstractTableColumn {

    protected AbstractTableColumn() {
    }

    /**
     * 列名
     */
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    protected void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
