package org.jeecf.gen.strategy;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.gen.model.BaseTable;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.engine.mysql.enums.WhereExpressEnum;
import org.jeecf.engine.mysql.model.query.WhereEntity;
import org.jeecf.gen.model.rule.FilterEntity;
import org.jeecf.gen.utils.TableHook;

/**
 * 过滤策略
 * 
 * @author jianyiming
 * @since 1.0
 */
public class FilterStrategy {

    public static String handler(List<FilterEntity> filterEntitys, TableHook builder,BaseTable baseTable) {
        List<WhereEntity> whereEntitys = new ArrayList<>();
        if (filterEntitys != null) {
            filterEntitys.forEach(filterEntity -> {
                WhereEntity whereEntity = WhereEntity.Builder.buildAnd(HumpUtils.humpToLine2(filterEntity.getField()), WhereExpressEnum.EQUALS, filterEntity.getValue());
                whereEntitys.add(whereEntity);
            });
        }
        return builder.getData(whereEntitys,baseTable);
    }

}
