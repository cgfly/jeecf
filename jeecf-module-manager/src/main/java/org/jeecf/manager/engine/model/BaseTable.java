package org.jeecf.manager.engine.model;
/**
 * 基础数据表
 * @author jianyiming
 *
 */
public class BaseTable extends AbstractTable{
	
	protected BaseTable() {}
	
	
	/**
	 * 实体名称
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
    

}
