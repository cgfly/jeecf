package org.jeecf.manager.engine.model;

import lombok.Data;
/**
 * 物理表字段
 * @author jianyiming
 *
 */
@Data
public class SchemaTableColumn {
	
	/**
	 * 表名
	 */
	private String tableName;
    /**
     * 列名称
     */
	private String name;
    /**
     * 注释
     */
	private String comment;
    /**
     * 是否主键
     */
	private Integer isKey;
	/**
	 * 是否为空
	 */
	private Integer isNull;
	/**
	 * jdbc类型
	 */
	private String jdbcType;
	/**
	 * 默认值 
	 */
	private String defaultValue;
    /**
     * 循序
     */
	private Integer sort;
	
}
