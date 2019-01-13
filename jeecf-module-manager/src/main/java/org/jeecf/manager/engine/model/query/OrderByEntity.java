package org.jeecf.manager.engine.model.query;

import org.jeecf.manager.engine.enums.SortModeEnum;
import org.jeecf.manager.engine.utils.JniValidate;
/**
 * 排序实体
 * @author jianyiming
 *
 */
public class OrderByEntity {
	
	protected OrderByEntity() {}
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

		public static OrderByEntity build(String columnName,SortModeEnum sortModeEnum) {
			OrderByEntity orderByEntity = new OrderByEntity();
			orderByEntity.setColumnName(JniValidate.columnValidate(columnName));
			orderByEntity.setSortMode(sortModeEnum.getName());
			return orderByEntity;
		}
		
	}

}
