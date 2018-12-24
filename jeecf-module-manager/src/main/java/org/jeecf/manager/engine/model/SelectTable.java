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

	private List<SelectTableColumn> selectTableColumns;

	private List<WhereEntity> whereEntitys;

	private List<OrderByEntity> orderByEntitys;

	private LimitEntity limitEntity;

	public List<SelectTableColumn> getSelectTableColumns() {
		return selectTableColumns;
	}

	public void setSelectTableColumns(List<SelectTableColumn> selectTableColumns) {
		this.selectTableColumns = selectTableColumns;
	}

	public List<WhereEntity> getWhereEntitys() {
		return whereEntitys;
	}

	public void setWhereEntitys(List<WhereEntity> whereEntitys) {
		this.whereEntitys = whereEntitys;
	}

	public List<OrderByEntity> getOrderByEntitys() {
		return orderByEntitys;
	}

	public void setOrderByEntitys(List<OrderByEntity> orderByEntitys) {
		this.orderByEntitys = orderByEntitys;
	}

	public LimitEntity getLimitEntity() {
		return limitEntity;
	}

	public void setLimitEntity(LimitEntity limitEntity) {
		this.limitEntity = limitEntity;
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
