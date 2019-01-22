package org.jeecf.manager.engine.model.insert;

import org.jeecf.manager.engine.model.BaseTableColumn;
import org.jeecf.manager.engine.utils.JniValidate;

/**
 * 插入表 列表
 * 
 * @author jianyiming
 *
 */
public class InsertTableColumn extends BaseTableColumn {

    protected InsertTableColumn() {
    }

    public static class Builder {

        public static InsertTableColumn build(String name, String columnName) {
            InsertTableColumn insertTableColumn = new InsertTableColumn();
            insertTableColumn.setColumnName(JniValidate.columnValidate(columnName));
            insertTableColumn.setName(name);
            return insertTableColumn;
        }
    }
}
