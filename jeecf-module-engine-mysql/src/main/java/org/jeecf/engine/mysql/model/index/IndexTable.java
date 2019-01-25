package org.jeecf.engine.mysql.model.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.engine.mysql.exception.TableColumnNullException;
import org.jeecf.engine.mysql.exception.TableNameEmptyException;
import org.jeecf.engine.mysql.model.AbstractTable;
import org.jeecf.engine.mysql.utils.JniValidate;

/**
 * 索引表
 * 
 * @author jianyiming
 *
 */
public class IndexTable extends AbstractTable {

    protected IndexTable() {
    };

    /**
     * 字段集合
     */
    private List<IndexTableColumn> indexTableColumns;

    public List<IndexTableColumn> getIndexTableColumns() {
        return indexTableColumns;
    }

    protected void setIndexTableColumns(List<IndexTableColumn> indexTableColumns) {
        this.indexTableColumns = indexTableColumns;
    }

    public static Builder builder() {
        return new Builder(new IndexTable());
    }

    public static class Builder {

        private IndexTable indexTable;

        protected Builder() {
        };

        protected Builder(IndexTable indexTable) {
            if (StringUtils.isEmpty(indexTable.getTableName())) {
                throw new TableNameEmptyException();
            }
            if (CollectionUtils.isEmpty(indexTable.getIndexTableColumns())) {
                throw new TableColumnNullException();
            }
            this.indexTable = indexTable;
        };

        public Builder setTableName(String tableName) {
            this.indexTable.setTableName(JniValidate.columnValidate(tableName));
            return this;
        }

        public Builder addIndexTableColumn(IndexTableColumn indexTableColumn) {
            if (indexTableColumn != null) {
                List<IndexTableColumn> indexColumns = this.indexTable.getIndexTableColumns();
                if (indexColumns == null) {
                    indexColumns = new ArrayList<>();
                    this.indexTable.setIndexTableColumns(indexColumns);
                }
                indexColumns.add(indexTableColumn);
            }
            return this;
        }

        public Builder addIndexTableColumns(List<IndexTableColumn> idnexTableColumns) {
            if (CollectionUtils.isNotEmpty(idnexTableColumns)) {
                List<IndexTableColumn> indexColumns = this.indexTable.getIndexTableColumns();
                if (indexColumns == null) {
                    indexColumns = new ArrayList<>();
                    this.indexTable.setIndexTableColumns(indexColumns);
                }
                for (IndexTableColumn idnexTableColumn : idnexTableColumns) {
                    indexColumns.add(idnexTableColumn);
                }
            }
            return this;
        }

    }

}
