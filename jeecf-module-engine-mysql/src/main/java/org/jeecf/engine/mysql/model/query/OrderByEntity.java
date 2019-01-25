package org.jeecf.engine.mysql.model.query;

import java.util.List;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.engine.mysql.enums.SortModeEnum;
import org.jeecf.engine.mysql.utils.JniValidate;

/**
 * 排序实体
 * 
 * @author jianyiming
 *
 */
public class OrderByEntity {

    protected OrderByEntity() {
    }

    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 排序方式
     */
    private String sortMode;

    public String getColumnName() {
        return columnName;
    }

    protected void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortMode() {
        return sortMode;
    }

    protected void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public static class Builder {

        public static OrderByEntity buildAsc(String columnName) {
            return OrderByEntity.Builder.build(columnName, SortModeEnum.ASC);
        }

        public static OrderByEntity buildDesc(String columnName) {
            return OrderByEntity.Builder.build(columnName, SortModeEnum.DESC);
        }

        public static OrderByEntity build(String columnName, SortModeEnum sortModeEnum) {
            OrderByEntity orderByEntity = new OrderByEntity();
            orderByEntity.setColumnName(JniValidate.columnValidate(columnName));
            orderByEntity.setSortMode(sortModeEnum.getName());
            return orderByEntity;
        }

    }
    
    /**
     * 构建orderBy语句
     * 
     * @param builder
     * @param name
     * @param orderByEntitys
     * @return
     */
    public static StringBuilder buildOrderByEntitys(StringBuilder builder, String name, List<OrderByEntity> orderByEntitys) {
        int size = orderByEntitys.size();
        builder.append("ORDER BY" + SplitCharEnum.BLANK.getName());
        for (int i = 0; i < size; i++) {
            OrderByEntity orderByEntity = orderByEntitys.get(i);
            builder.append(name + SplitCharEnum.DOT.getName() + orderByEntity.getColumnName() + SplitCharEnum.BLANK.getName());
            builder.append(orderByEntity.getSortMode() + SplitCharEnum.BLANK.getName());
            if (i < size - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        return builder;
    }

}
