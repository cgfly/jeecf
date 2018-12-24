package org.jeecf.manager.engine.model;

import java.util.List;

import org.jeecf.manager.engine.utils.JniValidate;

/**
 * 查询表
 * 
 * @author jianyiming
 *
 */
public class SelectTable extends BaseTable {

	protected SelectTable() {
	}

	private List<SelectTableColumn> columnList;

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

	public void setWhereEntitys(List<WhereEntity> whereEntitys) {
		this.whereEntitys = whereEntitys;
	}

	public static class Builder {

		public static SelectTable build(String name, String tableName) {
			SelectTable selectTable = new SelectTable();
			selectTable.setName(JniValidate.columnValidate(name));
			selectTable.setTableName(JniValidate.columnValidate(tableName));
			return selectTable;
		}
	}

}
