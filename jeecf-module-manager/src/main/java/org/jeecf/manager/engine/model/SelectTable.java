package org.jeecf.manager.engine.model;

import java.util.List;

/**
 * 查询表
 * @author jianyiming
 *
 */
public class SelectTable extends BaseTable {
	
	private List<SelectTableColumn>  columnList;
	
	private List<WhereEntity> whereEntitys;

	public List<SelectTableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<SelectTableColumn> columnList) {
		this.columnList = columnList;
	}

	public List<WhereEntity> getWhereEntitys() {
		return whereEntitys;
	}
	
	public void setWhereEntitys( List<WhereEntity> whereEntitys) {
		this.whereEntitys = whereEntitys;
	}

}
