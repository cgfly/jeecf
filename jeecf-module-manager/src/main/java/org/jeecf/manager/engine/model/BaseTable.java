package org.jeecf.manager.engine.model;
/**
 * 基础数据表
 * @author jianyiming
 *
 */
public class BaseTable {
	
	/**
	 * 实体名称
	 */
	private String name;
	/**
	 * 表名称
	 */
    private String tableName;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    

}
