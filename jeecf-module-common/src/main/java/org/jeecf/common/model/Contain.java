package org.jeecf.common.model;

import java.util.List;
/**
 * 包含实体
 * @author jianyiming
 *
 */
public class Contain {
	/**
	 * 字段名称
	 */
	private String columnName;
	
	/**
	 * 值 集合
	 */
	private List<String> columnValue;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public List<String> getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(List<String> columnValue) {
		this.columnValue = columnValue;
	}
	
}
