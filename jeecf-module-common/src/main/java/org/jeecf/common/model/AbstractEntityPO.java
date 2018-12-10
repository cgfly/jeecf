package org.jeecf.common.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.HumpUtils;
/**
 * 实体PO抽象类
 * @author jianyiming
 * 
 * @param <Q> 
 */
public abstract class AbstractEntityPO<Q> {
    /**
     * 通过实体参数构造
     * @param data
     */
	public AbstractEntityPO(Q data) {
		this.data = data;
	}
    /**
     * 通过请求实体构造
     * @param request
     */
	public <R> AbstractEntityPO(Request<Q,R> request) {
		this.data = request.getData();
		this.page = request.getPage();
		this.sorts = request.getSorts();
		this.schema = request.getSchema();
	}
    
	/**
	 * 实体参数
	 */
	private Q data;
	
	/**
	 * 指定返回结构
	 */
	private Object schema;
    
	/**
	 * 分页实体
	 */
	private Page page;
	
	/**
	 * 排序实体列表
	 */
	private List<Sort> sorts;
    /**
     * 包含实体列表
     */
	private List<Contain> contains;
	

	public Q getData() {
		return data;
	}

	public void setData(Q data) {
		this.data = data;
	}
	
	public Object getSchema() {
		return schema;
	}
	public void setSchema(Object schema) {
		this.schema = schema;
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}
	
	public List<Contain> getContains() {
		return contains;
	}

	public void setContains(List<Contain> contains) {
		this.contains = contains;
	}
    /**
     * 通过字段名和排序规则 赋值建排序列表
     * @param columnName 排序字段名
     * @param sortMode 排序规则
     */
	public void setSort(String columnName, String sortMode) {
		Sort sort = new Sort();
		sort.setColumnName(columnName);
		sort.setSortMode(sortMode);
		List<Sort> sorts = this.getSorts();
		if (sorts == null) {
			sorts = new ArrayList<Sort>();
			this.setSorts(sorts);
		}
		sorts.add(sort);
	}
    /**
     * 通过字段名和值 构建包含实体
     * @param columnName 字段名
     * @param values 值 多值以,分隔
     */
	public void setContain(String columnName, String values) {
		Contain contain = new Contain();
		contain.setColumnName(columnName);
		contain.setColumnValue(StringUtils.toSet(values));
		List<Contain> containList = this.getContains();
		if (containList == null) {
			containList = new ArrayList<Contain>();
			this.setContains(containList);
		}
		containList.add(contain);
	}
	
	public void setContain(Contain contain) {
		List<Contain> containList = this.getContains();
		if (containList == null) {
			containList = new ArrayList<Contain>();
			this.setContains(containList);
		}
		containList.add(contain);
	}
	
	/**
	 * 获取表名
	 * @return
	 */
	public abstract String getTableName();
    
	/**
	 * 构建排序 子类实现
	 */
	public abstract void buildSorts();

	/**
	 * 构建包含 子类实现
	 */
	public abstract void buildContains();
    
	/**
	 * 构建排序
	 * @param tableName 表名
	 */
	public void buildSorts(String tableName) {
		List<Sort> sortList = this.getSorts();
		if (CollectionUtils.isNotEmpty(sortList)) {
			for (Sort tempSort : sortList) {
				String columnName = tempSort.getColumnName();
				if (tempSort.getSortMode() != null) {
					tempSort.setSortMode(tempSort.getSortMode().toUpperCase());
				}
				if (StringUtils.isNotEmpty(columnName)) {
					String[] columnNames = columnName.split(",");
					for (String name : columnNames) {
						tempSort.setColumnName(tableName + "." + HumpUtils.humpToLine2(name).toLowerCase());
					}
				}
			}
		}
	}
    /**
     * 构建包含
     * @param tableName 表名
     */
	public void buildContains(String tableName) {
		List<Contain> containList = this.getContains();
		if (CollectionUtils.isNotEmpty(containList)) {
			for (Contain tempContain : containList) {
				String columnName = tempContain.getColumnName();
				if (StringUtils.isNotEmpty(columnName)) {
					tempContain.setColumnName(tableName + "." + HumpUtils.humpToLine2(columnName).toLowerCase());
				}
			}
		}
	}



}
