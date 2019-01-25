package org.jeecf.engine.mysql.model.query;

import org.jeecf.common.enums.SplitCharEnum;

/**
 * 分页实体
 * 
 * @author jianyiming
 *
 */
public class LimitEntity {

    protected LimitEntity() {
    }

    /**
     * 开始页大小
     */
    private int startNo;
    /**
     * 查询条数
     */
    private int size;

    public int getStartNo() {
        return startNo;
    }

    protected void setStartNo(int startNo) {
        this.startNo = startNo;
    }

    public int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    public static class Builder {

        public static LimitEntity build(int startNo, int size) {
            LimitEntity limitEntity = new LimitEntity();
            limitEntity.setStartNo(startNo);
            limitEntity.setSize(size);
            return limitEntity;
        }

    }
    
    /**
     * 构建limit语句
     * 
     * @param builder
     * @param limitEntity
     * @return
     */
    public static StringBuilder buildLimitEntity(StringBuilder builder, LimitEntity limitEntity) {
        builder.append("LIMIT" + SplitCharEnum.BLANK.getName());
        builder.append(limitEntity.getStartNo() + SplitCharEnum.BLANK.getName());
        builder.append(limitEntity.getSize() + SplitCharEnum.BLANK.getName());
        return builder;
    }

}
