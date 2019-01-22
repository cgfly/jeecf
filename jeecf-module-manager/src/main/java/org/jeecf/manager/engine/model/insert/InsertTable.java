package org.jeecf.manager.engine.model.insert;

import java.util.List;

import org.jeecf.manager.engine.model.BaseTable;
import org.jeecf.manager.engine.utils.JniValidate;

/**
 * 插入表
 * 
 * @author jianyiming
 *
 */
public class InsertTable extends BaseTable {

    protected InsertTable() {
    }

    /**
     * 字段集合
     */
    private List<InsertTableColumn> insertTableColumns;

    public List<InsertTableColumn> getInsertTableColumns() {
        return insertTableColumns;
    }

    public void setInsertTableColumns(List<InsertTableColumn> insertTableColumns) {
        this.insertTableColumns = insertTableColumns;
    }

    public static class Builder {

        public static InsertTable build(String name, String tableName) {
            InsertTable insertTable = new InsertTable();
            insertTable.setName(JniValidate.columnValidate(name));
            insertTable.setTableName(JniValidate.columnValidate(tableName));
            return insertTable;
        }

    }

}
