package org.jeecf.manager.engine.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.manager.engine.model.create.CreateTable;
import org.jeecf.manager.engine.model.create.CreateTableColumn;
import org.jeecf.manager.engine.model.query.LimitEntity;
import org.jeecf.manager.engine.model.query.OrderByEntity;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.model.query.SelectTableColumn;
import org.jeecf.manager.engine.model.query.WhereEntity;

import com.alibaba.druid.util.StringUtils;

/**
 * sql帮助类
 * 
 * @author jianyiming
 *
 */
public class SqlHelper {
    /**
     * 构建jdbcType
     * 
     * @param type          类型
     * @param length        长度
     * @param decimalLength 小数长度
     * @return
     */
    public static String toJdbcType(int type, int length, int decimalLength) {
        StringBuffer jdbcType = new StringBuffer(TableTypeEnum.getName(type));
        if (TableTypeEnum.INT.getCode() == type) {
            jdbcType.append(SplitCharEnum.LEFT_BRACKET.getName());
            jdbcType.append(length);
            jdbcType.append(SplitCharEnum.RIGHT_BRACKET.getName());
        } else if (TableTypeEnum.TINY_INT.getCode() == type) {
            jdbcType.append(SplitCharEnum.LEFT_BRACKET.getName());
            jdbcType.append(length);
            jdbcType.append(SplitCharEnum.RIGHT_BRACKET.getName());
        } else if (TableTypeEnum.BIG_INT.getCode() == type) {
            jdbcType.append(SplitCharEnum.LEFT_BRACKET.getName());
            jdbcType.append(length);
            jdbcType.append(SplitCharEnum.RIGHT_BRACKET.getName());
        } else if (TableTypeEnum.VARCHAR.getCode() == type) {
            jdbcType.append(SplitCharEnum.LEFT_BRACKET.getName());
            jdbcType.append(length);
            jdbcType.append(SplitCharEnum.RIGHT_BRACKET.getName());
        } else if (TableTypeEnum.DECIMAL.getCode() == type) {
            jdbcType.append(SplitCharEnum.LEFT_BRACKET.getName());
            jdbcType.append(length);
            jdbcType.append(SplitCharEnum.COMMA.getName());
            jdbcType.append(decimalLength);
            jdbcType.append(SplitCharEnum.RIGHT_BRACKET.getName());
        }
        return jdbcType.toString();
    }

    public static String toJdbcValue(String value) {
        if (StringUtils.isNumber(value)) {
            return value;
        }
        return SplitCharEnum.QUOT.getName() + value + SplitCharEnum.QUOT.getName();
    }

    /**
     * 建表sql
     * 
     * @param createTable
     * @return
     */
    public static String toCreateSql(CreateTable createTable) {
        List<CreateTableColumn> createTableColumns = createTable.getCreateTableColumns();
        StringBuilder builder = new StringBuilder("CREATE TABLE ");
        builder.append(createTable.getTableName());
        builder.append(SplitCharEnum.LEFT_BRACKET.getName() + SplitCharEnum.BLANK.getName());
        createTableColumns.forEach(createTableColumn -> {
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
        });
        builder.append(SplitCharEnum.RIGHT_BRACKET.getName() + SplitCharEnum.BLANK.getName());
        builder.append("COMMENT=");
        builder.append(SplitCharEnum.QUOT.getName() + createTable.getComment() + SplitCharEnum.QUOT.getName());
        return builder.toString();
    }

    /**
     * 删表sql
     * 
     * @param tableName
     * @return
     */
    public static String toDropSql(String tableName) {
        StringBuilder builder = new StringBuilder("DROP TABLE ");
        builder.append(JniValidate.columnValidate(tableName));
        return builder.toString();
    }

    /**
     * 查询sql
     * 
     * @param selectTable
     * @return
     */
    public static String toQuerySql(SelectTable selectTable) {
        List<SelectTableColumn> selectTableColumns = selectTable.getSelectTableColumns();
        List<WhereEntity> whereEntitys = selectTable.getWhereEntitys();
        List<OrderByEntity> orderByEntitys = selectTable.getOrderByEntitys();
        LimitEntity limitEntity = selectTable.getLimitEntity();
        StringBuilder builder = new StringBuilder("SELECT" + SplitCharEnum.BLANK.getName());
        int size = selectTableColumns.size();
        for (int i = 0; i < size; i++) {
            SelectTableColumn selectTableColumn = selectTableColumns.get(i);
            builder.append(selectTable.getName() + SplitCharEnum.DOT.getName() + selectTableColumn.getColumnName() + SplitCharEnum.BLANK.getName());
            builder.append("AS" + SplitCharEnum.BLANK.getName());
            builder.append(toJdbcValue(selectTableColumn.getName()));
            if (i < size - 1) {
                builder.append(SplitCharEnum.COMMA.getName());
            }
        }
        builder.append("FROM" + SplitCharEnum.BLANK.getName());
        builder.append(selectTable.getTableName() + SplitCharEnum.BLANK.getName());
        builder.append(selectTable.getName() + SplitCharEnum.BLANK.getName());
        if (CollectionUtils.isNotEmpty(whereEntitys)) {
            buildWhereEntitys(builder, selectTable.getName(), whereEntitys);
        }
        if (CollectionUtils.isNotEmpty(orderByEntitys)) {
            buildOrderByEntitys(builder, selectTable.getName(), orderByEntitys);
        }
        if (limitEntity != null) {
            buildLimitEntity(builder, limitEntity);
        }
        return builder.toString();
    }

    /**
     * 构建where语句
     * 
     * @param builder
     * @param name
     * @param whereEntitys
     * @return
     */
    private static StringBuilder buildWhereEntitys(StringBuilder builder, String name, List<WhereEntity> whereEntitys) {
        int size = whereEntitys.size();
        for (int i = 0; i < size; i++) {
            WhereEntity whereEntity = whereEntitys.get(i);
            if (i != 0) {
                builder.append(whereEntity.getConnector() + SplitCharEnum.BLANK.getName());
            }
            builder.append(name + SplitCharEnum.DOT.getName() + whereEntity.getColumn() + SplitCharEnum.BLANK.getName());
            builder.append(whereEntity.getExpress() + SplitCharEnum.BLANK.getName());
            builder.append(toJdbcValue(whereEntity.getValue()) + SplitCharEnum.BLANK.getName());
        }
        return builder;
    }

    /**
     * 构建orderBy语句
     * 
     * @param builder
     * @param name
     * @param orderByEntitys
     * @return
     */
    private static StringBuilder buildOrderByEntitys(StringBuilder builder, String name, List<OrderByEntity> orderByEntitys) {
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

    /**
     * 构建limit语句
     * 
     * @param builder
     * @param limitEntity
     * @return
     */
    private static StringBuilder buildLimitEntity(StringBuilder builder, LimitEntity limitEntity) {
        builder.append("LIMIT" + SplitCharEnum.BLANK.getName());
        builder.append(limitEntity.getStartNo() + SplitCharEnum.BLANK.getName());
        builder.append(limitEntity.getSize() + SplitCharEnum.BLANK.getName());
        return builder;
    }

}
