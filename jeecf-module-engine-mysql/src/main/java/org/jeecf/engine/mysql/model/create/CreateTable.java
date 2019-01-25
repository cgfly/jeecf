package org.jeecf.engine.mysql.model.create;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.engine.mysql.exception.TableColumnNullException;
import org.jeecf.engine.mysql.exception.TableCommentEmptyException;
import org.jeecf.engine.mysql.exception.TableNameEmptyException;
import org.jeecf.engine.mysql.model.AbstractTable;
import org.jeecf.engine.mysql.utils.JniValidate;

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

    /**
     * 建表sql
     * 
     * @return
     */
    public String toSql() {
        List<CreateTableColumn> createTableColumns = this.getCreateTableColumns();
        StringBuilder builder = new StringBuilder("CREATE TABLE ");
        builder.append(this.getTableName());
        builder.append(SplitCharEnum.LEFT_BRACKET.getName() + SplitCharEnum.BLANK.getName());
        for (int i = 0; i < createTableColumns.size(); i++) {
            CreateTableColumn createTableColumn = createTableColumns.get(i);
            builder.append(createTableColumn.getColumnName() + SplitCharEnum.BLANK.getName());
            builder.append(createTableColumn.getType() + SplitCharEnum.BLANK.getName());
            builder.append(createTableColumn.getNullModel() + SplitCharEnum.BLANK.getName());
            if (createTableColumn.getPrimaryKey() != null) {
                builder.append("COMMENT ");
                builder.append(SplitCharEnum.QUOT.getName() + createTableColumn.getComment() + SplitCharEnum.QUOT.getName() + SplitCharEnum.BLANK.getName());
                builder.append(createTableColumn.getPrimaryKey() + SplitCharEnum.BLANK.getName());
            } else {
                builder.append("DEFAULT ");
                builder.append(createTableColumn.getDefaultValue() + SplitCharEnum.BLANK.getName());
                builder.append("COMMENT ");
                builder.append(SplitCharEnum.QUOT.getName() + createTableColumn.getComment() + SplitCharEnum.QUOT.getName() + SplitCharEnum.BLANK.getName());
            }
            builder.append(createTableColumn.getColumnName() + SplitCharEnum.BLANK.getName());
            if (i < createTableColumns.size() - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        builder.append(SplitCharEnum.RIGHT_BRACKET.getName() + SplitCharEnum.BLANK.getName());
        builder.append("COMMENT=");
        builder.append(SplitCharEnum.QUOT.getName() + this.getComment() + SplitCharEnum.QUOT.getName());
        return builder.toString();
    }

}
