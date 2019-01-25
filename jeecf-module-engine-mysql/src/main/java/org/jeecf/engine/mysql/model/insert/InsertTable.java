package org.jeecf.engine.mysql.model.insert;

import java.util.List;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.engine.mysql.model.BaseTable;
import org.jeecf.engine.mysql.utils.JniValidate;
import org.jeecf.engine.mysql.utils.SqlHelper;

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

    /**
     * 插入表sql
     * 
     * @return
     */
    public String toSql() {
        List<InsertTableColumn> insertTableColumns = this.getInsertTableColumns();
        StringBuilder builder = new StringBuilder(" INSERT INTO ");
        builder.append(this.getTableName() + SplitCharEnum.BLANK.getName());
        builder.append(this.getName() + SplitCharEnum.BLANK.getName());
        for (int i = 0; i < insertTableColumns.size(); i++) {
            InsertTableColumn insertTableColumn = insertTableColumns.get(i);
            builder.append(insertTableColumn.getName());
            builder.append(SplitCharEnum.DOT.getName());
            builder.append(insertTableColumn.getColumnName() + SplitCharEnum.BLANK.getName());
            if (i < insertTableColumns.size() - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        builder.append(" VALUES ");
        for (int i = 0; i < insertTableColumns.size(); i++) {
            InsertTableColumn insertTableColumn = insertTableColumns.get(i);
            builder.append(SqlHelper.toJdbcValue(insertTableColumn.getName()));
            if (i < insertTableColumns.size() - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        return builder.toString();
    }

}
