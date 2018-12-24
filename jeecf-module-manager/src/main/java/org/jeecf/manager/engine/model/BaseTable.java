package org.jeecf.manager.engine.model;
/**
 * 基础数据表
 * @author jianyiming
 *
 */
public class BaseTable {
	
	protected BaseTable() {}
	
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
	protected void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}
    

}
