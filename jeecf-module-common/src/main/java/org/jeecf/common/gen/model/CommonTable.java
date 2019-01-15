package org.jeecf.common.gen.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.gen.enums.QueryTypeEnum;
/**
 * 
 * @author jianyiming
 *
 */
public class CommonTable{
	
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
	private List<BaseTableColumn> genTableColumns;

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

	public List<BaseTableColumn> getGenTableColumns() {
		return genTableColumns;
	}

	public void setGenTableColumns(List<BaseTableColumn> genTableColumns) {
		this.genTableColumns = genTableColumns;
	} 
	
	
	/**
	 * 获取查询字段集合
	 * 
	 * @return
	 */
	public List<BaseTableColumn> getQueryColumns() {
		List<BaseTableColumn> CommonTableeruyColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getQueryType() == 1) {
					CommonTableeruyColumns.add(column);
				}
			});
		}
		return CommonTableeruyColumns;
	}
    /**
     * 获取列表 字段集合
     * @return
     */
	public List<BaseTableColumn> getListColumns() {
		List<BaseTableColumn> listColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsList() == 1) {
					listColumns.add(column);
				}
			});
		}
		return listColumns;
	}

	/**
	 * 获取插入字段集合
	 * 
	 * @return
	 */
	public List<BaseTableColumn> getInsertColumns() {
		List<BaseTableColumn> insertColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsInsert() == 1) {
					insertColumns.add(column);
				}
			});
		}
		return insertColumns;
	}

	/**
	 * 获取更新字段集合
	 * 
	 * @return
	 */
	public List<BaseTableColumn> getUpdateColumns() {
		List<BaseTableColumn> updateColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				if (column.getIsEdit() == 1) {
					updateColumns.add(column);
				}
			});
		}
		return updateColumns;
	}
    /**
     * 获取开区间 或 闭区间 字段集合
     * @return
     */
	public List<BaseTableColumn> getIntervalColumns() {
		List<BaseTableColumn> intervalColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int CommonTableueryType = column.getQueryType();
				if (CommonTableueryType == QueryTypeEnum.OPEN_INTERVAL.getCode()
						|| CommonTableueryType == QueryTypeEnum.CLOSE_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}

    /**
     * 获取开区间 字段集合
     * @return
     */
	public List<BaseTableColumn> getOpenIntervalColumns() {
		List<BaseTableColumn> intervalColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int CommonTableueryType = column.getQueryType();
				if (CommonTableueryType == QueryTypeEnum.OPEN_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}
    /**
     * 获取闭区间 字段集合
     * @return
     */
	public List<BaseTableColumn> getCloseIntervalColumns() {
		List<BaseTableColumn> intervalColumns = new ArrayList<>();
		List<BaseTableColumn> genTableColumns = this.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(genTableColumns)) {
			genTableColumns.forEach(column -> {
				int CommonTableueryType = column.getQueryType();
				if (CommonTableueryType == QueryTypeEnum.CLOSE_INTERVAL.getCode()) {
					intervalColumns.add(column);
				}
			});
		}
		return intervalColumns;
	}
    /**
     * 类名 首字母大写
     * @return
     */
	public String getCapitalizeClassName() {
		return StringUtils.capitalize(this.getClassName());
	}
    /**
     * 类名 首字母小写
     * @return
     */
	public String getUncapitalizeClassName() {
		return StringUtils.uncapitalize(this.getClassName());
	}
}
