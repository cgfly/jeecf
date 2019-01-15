package org.jeecf.manager.gen.strategy;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.engine.enums.WhereExpressEnum;
import org.jeecf.manager.engine.model.query.WhereEntity;
import org.jeecf.manager.gen.builder.TableBuilder;
import org.jeecf.manager.gen.model.rule.FilterEntity;

/**
 * 过滤策略
 * 
 * @author jianyiming
 *
 */
public class FilterStrategy {

	public String handler(List<FilterEntity> filterEntitys, TableBuilder builder) {
		List<WhereEntity> whereEntitys = new ArrayList<>();
		if (filterEntitys != null) {
			filterEntitys.forEach(filterEntity -> {
				WhereEntity whereEntity = WhereEntity.Builder.buildAnd(HumpUtils.humpToLine2(filterEntity.getField()), WhereExpressEnum.EQUALS,
						filterEntity.getValue());
				whereEntitys.add(whereEntity);
			});
		}
		return builder.getData(whereEntitys);
	}

}
