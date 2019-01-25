package org.jeecf.engine.mysql.model.query;

import java.util.List;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.engine.mysql.enums.WhereConnectorEnum;
import org.jeecf.engine.mysql.enums.WhereExpressEnum;
import org.jeecf.engine.mysql.utils.JniValidate;
import org.jeecf.engine.mysql.utils.SqlHelper;

/**
 * 查询条件实体
 * 
 * @author jianyiming
 *
 */
public class WhereEntity {
    /**
     * 字段
     */
    private String column;
    /**
     * 表达式
     */
    private String express;
    /**
     * 连接方式
     */
    private String connector;
    /**
     * 值
     */
    private String value;

    protected WhereEntity() {
    }

    public String getColumn() {
        return column;
    }

    protected void setColumn(String column) {
        this.column = column;
    }

    public String getExpress() {
        return express;
    }

    protected void setExpress(String express) {
        this.express = express;
    }

    public String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    public String getConnector() {
        return connector;
    }

    protected void setConnector(String connector) {
        this.connector = connector;
    }

    public static class Builder {

        public static WhereEntity buildOr(String column, WhereExpressEnum expressEnum, String value) {
            return build(column, WhereConnectorEnum.OR.getName(), expressEnum.getName(), value);
        }

        public static WhereEntity buildAnd(String column, WhereExpressEnum expressEnum, String value) {
            return build(column, WhereConnectorEnum.AND.getName(), expressEnum.getName(), value);
        }

        private static WhereEntity build(String column, String connector, String express, String value) {
            WhereEntity whereEntity = new WhereEntity();
            column = JniValidate.columnValidate(column);
            whereEntity.setColumn(column);
            whereEntity.setConnector(connector);
            whereEntity.setExpress(express);
            whereEntity.setValue(value);
            return whereEntity;
        }
    }
    
    /**
     * 构建where语句
     * 
     * @param builder
     * @param name
     * @param whereEntitys
     * @return
     */
    public static StringBuilder buildWhereEntitys(StringBuilder builder, String name, List<WhereEntity> whereEntitys) {
        int size = whereEntitys.size();
        for (int i = 0; i < size; i++) {
            WhereEntity whereEntity = whereEntitys.get(i);
            if (i != 0) {
                builder.append(whereEntity.getConnector() + SplitCharEnum.BLANK.getName());
            }
            builder.append(name + SplitCharEnum.DOT.getName() + whereEntity.getColumn() + SplitCharEnum.BLANK.getName());
            builder.append(whereEntity.getExpress() + SplitCharEnum.BLANK.getName());
            builder.append(SqlHelper.toJdbcValue(whereEntity.getValue()) + SplitCharEnum.BLANK.getName());
        }
        return builder;
    }

}
