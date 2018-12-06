package org.jeecf.manager.engine.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.engine.model.SchemaTable;
import org.jeecf.manager.engine.model.SchemaTableColumn;

/**
 * 物理表dao
 * @author jianyiming
 *
 */
@Mapper 
public interface SchemaTableDao {
	/**
	 * 查询物理表 列表信息
	 * @param physicalTable
	 * @return
	 */
	public List<SchemaTable> findTableList(SchemaTable schemaTable);
	/**
	 * 查询物理表 字段列表信息
	 * @param physicalTableColumn
	 * @return
	 */
	public List<SchemaTableColumn> findTableColumnList(SchemaTableColumn schemaTableColumn);
}
