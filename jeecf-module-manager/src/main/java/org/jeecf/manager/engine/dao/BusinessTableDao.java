package org.jeecf.manager.engine.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.engine.model.SelectTable;
/**
 * 表查询
 * @author jianyiming
 *
 */
@Mapper
public interface BusinessTableDao {

	/**
	 * 查询所有字段的信息
	 * @param selectTable
	 * @return
	 */
	public List<Map<String,Object >> queryAll(SelectTable selectTable);

}
