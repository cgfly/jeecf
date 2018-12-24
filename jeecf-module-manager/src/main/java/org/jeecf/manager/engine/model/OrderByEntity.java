package org.jeecf.manager.engine.model;

import org.jeecf.common.enums.SplitCharEnum;
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

		public static OrderByEntity buildAsc(String name,String columnName) {
			return OrderByEntity.Builder.build(name,columnName, SortModeEnum.ASC.getName());
		}
		public static OrderByEntity buildDesc(String name,String columnName) {
			return OrderByEntity.Builder.build(name,columnName, SortModeEnum.DESC.getName());
		}

		private static OrderByEntity build(String name,String columnName,String sortModel) {
			OrderByEntity orderByEntity = new OrderByEntity();
			orderByEntity.setColumnName(JniValidate.columnValidate(name + SplitCharEnum.DOT.getName() +columnName));
			orderByEntity.setSortMode(sortModel);
			return orderByEntity;
		}
		
	}

}
