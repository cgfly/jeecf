package org.jeecf.manager.gen.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.enums.EnumUtils;
/**
 * 代码表基础表
 * @author jianyiming
 *
 * @param <T>
 * @param <Q>
 */
public class BaseTable<T extends BaseTableColumn,Q extends CommonTable<T>> {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 注释
	 */
	private String comment;

	/**
	 * 父表外键
	 */
	private String parentTableFk;

	/**
	 * 父表外键
	 */
	private Q parent;

	/**
	 * 表字段
	 */
	private List<T> genTableColumns;

	/**
	 * 子表
	 */
	private List<Q> childList = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getParentTableFk() {
		return parentTableFk;
	}

	public void setParentTableFk(String parentTableFk) {
		this.parentTableFk = parentTableFk;
	}

	public Q getParent() {
		return parent;
	}

	public void setParent(Q parent) {
		this.parent = parent;
	}

	public List<T> getGenTableColumns() {
		return genTableColumns;
	}

	public void setGenTableColumns(List<T> genTableColumns) {
		this.genTableColumns = genTableColumns;
	}

	public List<Q> getChildList() {
		return childList;
	}

	public void setChildList(List<Q> childList) {
		this.childList = childList;
	}

	public List<T> getQueryColumns() {
		List<T> qeruyColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsQuery() == 1) {
					qeruyColumns.add(column);
				}
			});
		}
		return qeruyColumns;
	}

	public List<T> getlistColumns() {
		List<T> listColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsList() == 1) {
					listColumns.add(column);
				}
			});
		}
		return listColumns;
	}

	public List<T> getInsertColumns() {
		List<T> insertColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsInsert() == 1) {
					insertColumns.add(column);
				}
			});
		}
		return insertColumns;
	}

	public List<T> getUpdateColumns() {
		List<T> updateColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsEdit() == 1) {
					updateColumns.add(column);
				}
			});
		}
		return updateColumns;
	}

	public List<T> getIntervalColumns() {
		List<T> intervalColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType == EnumUtils.QueryType.OPEN_INTERVAL.getCode() || queryType == EnumUtils.QueryType.CLOSE_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}

	public List<T> getOpenIntervalColumns() {
		List<T> intervalColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType == EnumUtils.QueryType.OPEN_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}

	public List<T> getCloseIntervalColumns() {
		List<T> intervalColumns = new ArrayList<>();
		List<T> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType == EnumUtils.QueryType.CLOSE_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}
	
	public String getCapitalizeClassName() {
		return StringUtils.capitalize(this.getClassName());
	}
	
	public String getUncapitalizeClassName() {
		return StringUtils.uncapitalize(this.getClassName());
	}
	
}
