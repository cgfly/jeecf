package org.jeecf.engine.mysql.model.query;

import org.jeecf.engine.mysql.model.BaseTableColumn;
import org.jeecf.engine.mysql.utils.JniValidate;

/**
 * 查询表字段
 * 
 * @author jianyiming
 *
 */
public class SelectTableColumn extends BaseTableColumn {

    protected SelectTableColumn() {
    }

    public static class Builder {

        public static SelectTableColumn build(String name, String columnName) {
            SelectTableColumn selectTableColumn = new SelectTableColumn();
            selectTableColumn.setColumnName(JniValidate.columnValidate(columnName));
            selectTableColumn.setName(name);
            return selectTableColumn;
        }
    }

}
