package org.jeecf.manager.engine.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.engine.model.PhysicalTable;
import org.jeecf.manager.engine.model.PhysicalTableColumn;

/**
 * 物理表dao
 * @author jianyiming
 *
 */
@Mapper 
public interface PhysicalTableDao {
	/**
	 * 查询物理表 列表信息
	 * @param physicalTable
	 * @return
	 */
	public List<PhysicalTable> findTableList(PhysicalTable physicalTable);
	/**
	 * 查询物理表 字段列表信息
	 * @param physicalTableColumn
	 * @return
	 */
	public List<PhysicalTableColumn> findTableColumnList(PhysicalTableColumn physicalTableColumn);
}
