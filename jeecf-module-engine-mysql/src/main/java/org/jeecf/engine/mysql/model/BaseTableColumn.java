package org.jeecf.engine.mysql.model;

/**
 * 基础表字典
 * 
 * @author jianyiming
 *
 */
public class BaseTableColumn extends AbstractTableColumn {

    protected BaseTableColumn() {
    }

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

}
