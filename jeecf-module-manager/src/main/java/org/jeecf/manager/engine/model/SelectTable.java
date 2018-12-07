package org.jeecf.manager.engine.model;

import java.util.List;

/**
 * 查询表
 * @author jianyiming
 *
 */
public class SelectTable extends BaseTable {
	
	private List<SelectTableColumn>  columnList;

	public List<SelectTableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<SelectTableColumn> columnList) {
		this.columnList = columnList;
	}
	
}
