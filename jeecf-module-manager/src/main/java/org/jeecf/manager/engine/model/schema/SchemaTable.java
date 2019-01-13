package org.jeecf.manager.engine.model.schema;

import lombok.Data;
/**
 * 物理表 实体
 * @author jianyiming
 *
 */
@Data
public class SchemaTable {
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
