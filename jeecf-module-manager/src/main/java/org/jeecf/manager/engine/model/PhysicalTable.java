package org.jeecf.manager.engine.model;

import lombok.Data;
/**
 * 物理表 实体
 * @author jianyiming
 *
 */
@Data
public class PhysicalTable {
	/**
	 * 表名
	 */
	private String name;
	/**
	 * 注释
	 */
	private String comment;
	/**
	 * 下次自增的值
	 */
	private Long autoIncrement;

	
	
	
}
