package org.jeecf.manager.engine.model;

/**
 * 基础表字典
 * 
 * @author jianyiming
 *
 */
public class BaseTableColumn {

	
	protected BaseTableColumn() {}
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 列名
	 */
	private String columnName;

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getColumnName() {
		return columnName;
	}

	protected void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
