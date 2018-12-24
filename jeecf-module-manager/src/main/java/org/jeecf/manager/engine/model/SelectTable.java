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
	
	private List<OrderByEntity> orderByEntitys;
	
	private LimitEntity limitEntity;

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
	
}
