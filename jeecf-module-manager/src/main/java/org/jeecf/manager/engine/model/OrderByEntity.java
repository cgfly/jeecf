package org.jeecf.manager.engine.model;

import org.jeecf.manager.engine.enums.SortModeEnum;
import org.jeecf.manager.engine.utils.JniValidate;
/**
 * 排序实体
 * @author jianyiming
 *
 */
public class OrderByEntity {
	
	protected OrderByEntity() {}

	private String columnName;

	private String sortMode;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public static class Builder {

		public static OrderByEntity buildAsc(String columnName) {
			return OrderByEntity.Builder.build(columnName, SortModeEnum.ASC.getName());
		}
		public static OrderByEntity buildDesc(String columnName) {
			return OrderByEntity.Builder.build(columnName, SortModeEnum.DESC.getName());
		}

		private static OrderByEntity build(String columnName,String sortModel) {
			OrderByEntity orderByEntity = new OrderByEntity();
			orderByEntity.setColumnName(JniValidate.columnValidate(columnName));
			orderByEntity.setSortMode(sortModel);
			return orderByEntity;
		}
	}

}
