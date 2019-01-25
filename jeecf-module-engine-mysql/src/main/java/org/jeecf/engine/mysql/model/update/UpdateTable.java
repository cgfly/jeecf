package org.jeecf.engine.mysql.model.update;

import java.util.List;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.engine.mysql.model.BaseTable;
import org.jeecf.engine.mysql.utils.JniValidate;
import org.jeecf.engine.mysql.utils.SqlHelper;

/**
 * 更新表
 * 
 * @author jianyiming
 *
 */
public class UpdateTable extends BaseTable {

    protected UpdateTable() {
    }

    /**
     * 主键
     */
    private Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    private List<UpdateTableColumn> updateTableColumns;

    public List<UpdateTableColumn> getUpdateTableColumns() {
        return updateTableColumns;
    }

    public void setUpdateTableColumns(List<UpdateTableColumn> updateTableColumns) {
        this.updateTableColumns = updateTableColumns;
    }

    public static class Builder {

        public static UpdateTable build(String name, String tableName) {
            UpdateTable updateTable = new UpdateTable();
            updateTable.setName(JniValidate.columnValidate(name));
            updateTable.setTableName(JniValidate.columnValidate(tableName));
            return updateTable;
        }

    }

    /**
     * 更新表sql
     * 
     * @return
     */
    public String toSql() {
        List<UpdateTableColumn> updateTableColumns = this.getUpdateTableColumns();
        StringBuilder builder = new StringBuilder("UPDATE ");
        builder.append(this.getTableName() + SplitCharEnum.BLANK.getName());
        builder.append(this.getName() + SplitCharEnum.BLANK.getName());
        for(int i = 0 ; i < updateTableColumns.size() ; i++) {
            UpdateTableColumn updateTableColumn = updateTableColumns.get(i);
            builder.append(updateTableColumn.getColumnName());
            builder.append(" = ");
            builder.append(SqlHelper.toJdbcValue(updateTableColumn.getName())+ SplitCharEnum.BLANK.getName());
            if (i < updateTableColumns.size() - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        builder.append(" WHERE id = ");
        builder.append(SqlHelper.toJdbcValue(this.getId().toString()));
        return builder.toString();
    }

}
