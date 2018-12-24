package org.jeecf.manager.engine.utils;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.engine.model.OrderByEntity;
/**
 * sql 构建帮助类
 * @author jianyiming
 *
 */
public class SqlHelper {
	
	/**
	 * 构建排序
	 * @param tableName 表名
	 */
	public static void buildSorts(String tableName,List<OrderByEntity> orderByEntitys) {
		if (CollectionUtils.isNotEmpty(orderByEntitys)) {
			for (OrderByEntity orderByEntity : orderByEntitys) {
				String columnName = orderByEntity.getColumnName();
				if (orderByEntity.getSortMode() != null) {
					orderByEntity.setSortMode(orderByEntity.getSortMode().toUpperCase());
				}
				if (StringUtils.isNotEmpty(columnName)) {
					String[] columnNames = columnName.split(",");
					for (String name : columnNames) {
						orderByEntity.setColumnName(JniValidate.columnValidate(tableName + "." + HumpUtils.humpToLine2(name).toLowerCase()));
					}
				}
			}
		}
	}

}
