package org.jeecf.gen.utils;

import java.util.List;

import org.jeecf.engine.mysql.model.query.WhereEntity;
import org.jeecf.gen.model.BaseTable;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public interface TableHook {
    /**
     * 构建表信息
     * 
     * @param tableName
     * @return
     */
    public BaseTable build(String tableName);

    /**
     * 获取表数据
     * 
     * @param whereEntitys
     * @param baseTable
     * @return
     */
    public String getData(List<WhereEntity> whereEntitys, BaseTable baseTable);

}
