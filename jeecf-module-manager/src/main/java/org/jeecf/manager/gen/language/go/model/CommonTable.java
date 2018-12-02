package org.jeecf.manager.gen.language.go.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.enums.EnumUtils;
/**
 * 
 * @author jianyiming
 *
 */
public class CommonTable {
	
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
	 * 表字段
	 */
	private List<GenTableColumn> genTableColumns;

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

	public List<GenTableColumn> getGenTableColumns() {
		return genTableColumns;
	}

	public void setGenTableColumns(List<GenTableColumn> genTableColumns) {
		this.genTableColumns = genTableColumns;
	} 
	
	
	public List<GenTableColumn> getQueryColumns() {
		List<GenTableColumn> qeruyColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsQuery() == 1) {
					qeruyColumns.add(column);
				}
			});
		}
		return qeruyColumns;
	}

	public List<GenTableColumn> getlistColumns() {
		List<GenTableColumn> listColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsList() == 1) {
					listColumns.add(column);
				}
			});
		}
		return listColumns;
	}

	public List<GenTableColumn> getInsertColumns() {
		List<GenTableColumn> insertColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsInsert() == 1) {
					insertColumns.add(column);
				}
			});
		}
		return insertColumns;
	}

	public List<GenTableColumn> getUpdateColumns() {
		List<GenTableColumn> updateColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsEdit() == 1) {
					updateColumns.add(column);
				}
			});
		}
		return updateColumns;
	}

	public List<GenTableColumn> getIntervalColumns() {
		List<GenTableColumn> intervalColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType == EnumUtils.QueryType.CLOSE_INTERVAL.getCode() || queryType == EnumUtils.QueryType.OPEN_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}

	public List<GenTableColumn> getOpenIntervalColumns() {
		List<GenTableColumn> intervalColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType ==  EnumUtils.QueryType.OPEN_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}

	public List<GenTableColumn> getCloseIntervalColumns() {
		List<GenTableColumn> intervalColumns = new ArrayList<>();
		List<GenTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int queryType = column.getQueryType();
				if (queryType ==  EnumUtils.QueryType.CLOSE_INTERVAL.getCode()) {
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
