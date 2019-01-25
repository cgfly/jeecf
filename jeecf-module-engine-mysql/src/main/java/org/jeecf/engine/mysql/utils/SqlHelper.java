package org.jeecf.engine.mysql.utils;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.engine.mysql.enums.TableTypeEnum;

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
        if (StringUtils.isNumeric(value)) {
            return value;
        }
        return SplitCharEnum.QUOT.getName() + value + SplitCharEnum.QUOT.getName();
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
     * 获取 schema table sql
     * 
     * @param name
     * @return
     */
    public static String toSchemaTableSql(String name) {
        StringBuilder builder = new StringBuilder(" SELECT t.table_name AS name,t.TABLE_COMMENT AS comment FROM information_schema.`TABLES` t WHERE t.TABLE_SCHEMA = (select database()) ");
        if (StringUtils.isNotEmpty(name)) {
            builder.append(" AND t.TABLE_NAME = (");
            builder.append(SqlHelper.toJdbcValue(name));
            builder.append(")");
        }
        builder.append(" ORDER BY t.TABLE_NAME ");
        return builder.toString();
    }

    /**
     * 获取 schema table column sql
     * 
     * @param name
     * @return
     */
    public static String toSchemaTableColumnSql(String tableName) {
        StringBuilder builder = new StringBuilder(
                " SELECT t.COLUMN_NAME AS 'name', t.table_name AS 'tableName',(CASE WHEN t.IS_NULLABLE = 'YES' THEN '0' ELSE '1' END) AS 'isNull',(CASE WHEN t.COLUMN_KEY = 'PRI'  THEN '1' ELSE '0' END  ) AS 'isKey',(t.ORDINAL_POSITION * 10) AS 'sort',t.COLUMN_COMMENT AS 'comment',t.COLUMN_TYPE AS 'jdbcType' FROM information_schema.`COLUMNS` t  WHERE t.TABLE_SCHEMA = (select database())  ");
        if (StringUtils.isNotEmpty(tableName)) {
            builder.append(" AND t.TABLE_NAME = (");
            builder.append(SqlHelper.toJdbcValue(tableName));
            builder.append(")");
        }
        builder.append(" ORDER BY t.ORDINAL_POSITION ");
        return builder.toString();
    }

}
