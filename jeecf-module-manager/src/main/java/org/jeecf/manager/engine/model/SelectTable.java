package org.jeecf.manager.engine.model;

import java.util.List;

/**
 * 查询表
 * @author jianyiming
 *
 */
public class SelectTable extends BaseTable {
	
	private List<SelectTableColumn>  columnList;
	
	private String sql;

	public List<SelectTableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<SelectTableColumn> columnList) {
		this.columnList = columnList;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
