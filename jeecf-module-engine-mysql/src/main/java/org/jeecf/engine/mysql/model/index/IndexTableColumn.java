package org.jeecf.engine.mysql.model.index;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.engine.mysql.enums.IndexTypeEnum;
import org.jeecf.engine.mysql.enums.IndexWayEnum;
import org.jeecf.engine.mysql.exception.IndexColumnNameEmptyException;
import org.jeecf.engine.mysql.exception.IndexCommentEmptyException;
import org.jeecf.engine.mysql.exception.IndexNameEmptyException;
import org.jeecf.engine.mysql.model.AbstractTableColumn;
import org.jeecf.engine.mysql.utils.JniValidate;

/**
 * 索引表字段
 * 
 * @author jianyiming
 *
 */
public class IndexTableColumn extends AbstractTableColumn {

    protected IndexTableColumn() {
    };

    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 方法
     */
    private String way;
    /**
     * 注释
     */
    private String comment;

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getWay() {
        return way;
    }

    protected void setWay(String way) {
        this.way = way;
    }

    public String getComment() {
        return comment;
    }

    protected void setComment(String comment) {
        this.comment = comment;
    }

    public static Builder builder() {
        return new Builder(new IndexTableColumn());
    }

    public static class Builder {

        private IndexTableColumn indexTableColumn;

        protected Builder() {
        };

        public IndexTableColumn build() {
            if (StringUtils.isEmpty(this.indexTableColumn.getName())) {
                throw new IndexNameEmptyException();
            }
            if (StringUtils.isEmpty(this.indexTableColumn.getColumnName())) {
                throw new IndexColumnNameEmptyException();
            }
            if (StringUtils.isEmpty(this.indexTableColumn.getType())) {
                this.indexTableColumn.setType(IndexTypeEnum.NORMAL.getName());
            }
            if (StringUtils.isEmpty(this.indexTableColumn.getWay())) {
                this.indexTableColumn.setWay(IndexWayEnum.HASH.getName());
            }
            if (StringUtils.isEmpty(this.indexTableColumn.getComment())) {
                throw new IndexCommentEmptyException();
            }
            return this.indexTableColumn;
        }

        protected Builder(IndexTableColumn indexTableColumn) {
            this.indexTableColumn = indexTableColumn;
        };

        public Builder setName(String name) {
            this.indexTableColumn.setType(JniValidate.columnValidate(name));
            return this;
        }

        public Builder setColumnName(String columnName) {
            this.indexTableColumn.setColumnName(JniValidate.columnValidate(columnName));
            return this;
        }

        public Builder setComment(String comment) {
            this.indexTableColumn.setComment(comment);
            return this;
        }

        public Builder setType(IndexTypeEnum indexTypeEnum) {
            this.indexTableColumn.setType(indexTypeEnum.getName());
            return this;
        }

        public Builder setWay(IndexWayEnum indexWayEnum) {
            this.indexTableColumn.setWay(indexWayEnum.getName());
            return this;
        }
    }

}
