package org.jeecf.common.model;

import java.util.Set;
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
	private Set<String> columnValue;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Set<String> getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(Set<String> columnValue) {
		this.columnValue = columnValue;
	}
	
}
