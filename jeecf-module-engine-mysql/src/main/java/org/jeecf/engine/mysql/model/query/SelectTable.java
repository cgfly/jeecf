package org.jeecf.engine.mysql.model.query;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.engine.mysql.model.BaseTable;
import org.jeecf.engine.mysql.utils.JniValidate;
import org.jeecf.engine.mysql.utils.SqlHelper;

/**
 * 查询表
 * 
 * @author jianyiming
 *
 */
public class SelectTable extends BaseTable {

    protected SelectTable() {
    }

    /**
     * 字段集合
     */
    private List<SelectTableColumn> selectTableColumns;
    /**
     * 条件实体集合
     */
    private List<WhereEntity> whereEntitys;
    /**
     * 排序实体集合
     */
    private List<OrderByEntity> orderByEntitys;
    /**
     * 分页实体
     */
    private LimitEntity limitEntity;

    public List<SelectTableColumn> getSelectTableColumns() {
        return selectTableColumns;
    }

    public void setSelectTableColumns(List<SelectTableColumn> selectTableColumns) {
        this.selectTableColumns = selectTableColumns;
    }

    public List<WhereEntity> getWhereEntitys() {
        return whereEntitys;
    }

    public void setWhereEntitys(List<WhereEntity> whereEntitys) {
        this.whereEntitys = whereEntitys;
    }

    public List<OrderByEntity> getOrderByEntitys() {
        return orderByEntitys;
    }

    public void setOrderByEntitys(List<OrderByEntity> orderByEntitys) {
        this.orderByEntitys = orderByEntitys;
    }

    public LimitEntity getLimitEntity() {
        return limitEntity;
    }

    public void setLimitEntity(LimitEntity limitEntity) {
        this.limitEntity = limitEntity;
    }

    public static class Builder {

        public static SelectTable build(String name, String tableName) {
            SelectTable selectTable = new SelectTable();
            selectTable.setName(JniValidate.columnValidate(name));
            selectTable.setTableName(JniValidate.columnValidate(tableName));
            return selectTable;
        }

    }

    /**
     * 查询sql
     * 
     * @return
     */
    public String toSql() {
        List<SelectTableColumn> selectTableColumns = this.getSelectTableColumns();
        List<WhereEntity> whereEntitys = this.getWhereEntitys();
        List<OrderByEntity> orderByEntitys = this.getOrderByEntitys();
        LimitEntity limitEntity = this.getLimitEntity();
        StringBuilder builder = new StringBuilder("SELECT" + SplitCharEnum.BLANK.getName());
        int size = selectTableColumns.size();
        for (int i = 0; i < size; i++) {
            SelectTableColumn selectTableColumn = selectTableColumns.get(i);
            builder.append(this.getName() + SplitCharEnum.DOT.getName() + selectTableColumn.getColumnName() + SplitCharEnum.BLANK.getName());
            builder.append("AS" + SplitCharEnum.BLANK.getName());
            builder.append(SqlHelper.toJdbcValue(selectTableColumn.getName()));
            if (i < size - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        builder.append("FROM" + SplitCharEnum.BLANK.getName());
        builder.append(this.getTableName() + SplitCharEnum.BLANK.getName());
        builder.append(this.getName() + SplitCharEnum.BLANK.getName());
        if (CollectionUtils.isNotEmpty(whereEntitys)) {
            WhereEntity.buildWhereEntitys(builder, this.getName(), whereEntitys);
        }
        if (CollectionUtils.isNotEmpty(orderByEntitys)) {
            OrderByEntity.buildOrderByEntitys(builder, this.getName(), orderByEntitys);
        }
        if (limitEntity != null) {
            LimitEntity.buildLimitEntity(builder, limitEntity);
        }
        return builder.toString();
    }

}
