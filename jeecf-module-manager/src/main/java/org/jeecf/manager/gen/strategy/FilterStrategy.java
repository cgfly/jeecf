package org.jeecf.manager.gen.strategy;

import java.util.List;

import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.gen.builder.AbstractLanguageBuilder;
import org.jeecf.manager.gen.model.rule.FilterEntity;
/**
 * 过滤策略
 * @author jianyiming
 *
 */
public class FilterStrategy {
	
	public String handler(List<FilterEntity> filterEntitys,AbstractLanguageBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		if (filterEntitys != null) {
			filterEntitys.forEach(filterEntity -> {
				buffer.append(" and ");
				buffer.append(HumpUtils.humpToLine2(filterEntity.getField()));
				buffer.append(" = '");
				buffer.append(filterEntity.getValue());
				buffer.append("' ");
			});
		}
		return builder.getData(buffer.toString());
	}

}
