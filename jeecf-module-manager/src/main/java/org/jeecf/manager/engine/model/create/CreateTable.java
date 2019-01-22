package org.jeecf.manager.engine.model.create;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.engine.exception.TableColumnNullException;
import org.jeecf.manager.engine.exception.TableCommentEmptyException;
import org.jeecf.manager.engine.exception.TableNameEmptyException;
import org.jeecf.manager.engine.model.AbstractTable;
import org.jeecf.manager.engine.utils.JniValidate;

import com.alibaba.druid.util.StringUtils;

/**
 * 创建表
 * 
 * @author jianyiming
 *
 */
public class CreateTable extends AbstractTable {

    protected CreateTable() {
    };

    /**
     * 字段集合
     */
    private List<CreateTableColumn> createTableColumns;
    /**
     * 注释
     */
    private String comment;

    public List<CreateTableColumn> getCreateTableColumns() {
        return createTableColumns;
    }

    protected void setCreateTableColumns(List<CreateTableColumn> createTableColumns) {
        this.createTableColumns = createTableColumns;
    }

    public String getComment() {
        return comment;
    }

    protected void setComment(String comment) {
        this.comment = comment;
    }

    public static Builder builder() {
        return new Builder(new CreateTable());
    }

    public static class Builder {

        private CreateTable createTable;

        protected Builder() {
        };

        protected Builder(CreateTable createTable) {
            this.createTable = createTable;
        };

        public CreateTable build() {
            if (StringUtils.isEmpty(this.createTable.getComment())) {
                throw new TableCommentEmptyException();
            }
            if (StringUtils.isEmpty(this.createTable.getTableName())) {
                throw new TableNameEmptyException();
            }
            if (CollectionUtils.isEmpty(this.createTable.getCreateTableColumns())) {
                throw new TableColumnNullException();
            }
            return this.createTable;
        }

        public Builder setComment(String comment) {
            this.createTable.setComment(comment);
            return this;
        }

        public Builder setTableName(String tableName) {
            this.createTable.setTableName(JniValidate.columnValidate(tableName));
            return this;
        }

        public Builder addCreateTableColumn(CreateTableColumn createTableColumn) {
            if (createTableColumn != null) {
                List<CreateTableColumn> tableColumns = this.createTable.getCreateTableColumns();
                if (tableColumns == null) {
                    tableColumns = new ArrayList<>();
                    this.createTable.setCreateTableColumns(tableColumns);
                }
                tableColumns.add(createTableColumn);
            }
            return this;
        }

        public Builder addCreateTableColumns(List<CreateTableColumn> createTableColumns) {
            if (CollectionUtils.isNotEmpty(createTableColumns)) {
                List<CreateTableColumn> tableColumns = this.createTable.getCreateTableColumns();
                if (tableColumns == null) {
                    tableColumns = new ArrayList<>();
                    this.createTable.setCreateTableColumns(tableColumns);
                }
                for (CreateTableColumn createTableColumn : createTableColumns) {
                    tableColumns.add(createTableColumn);
                }
            }
            return this;
        }
    }

}
