package org.jeecf.manager.engine.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.engine.model.create.CreateTable;
import org.jeecf.manager.engine.model.index.IndexTable;
import org.jeecf.manager.engine.model.insert.InsertTable;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.model.update.UpdateTable;
/**
 * 表查询
 * @author jianyiming
 *
 */
@Mapper
public interface BusinessTableDao {

	/**
	 * 查询数据
	 * @param selectTable
	 * @return
	 */
	public List<Map<String,Object >> query(SelectTable selectTable);
	/**
	 * 插入数据
	 * @param insertTable
	 * @return
	 */
	public int query(InsertTable insertTable);
	
	/**
	 * 更新数据
	 * @param updateTable
	 * @return
	 */
	public int update(UpdateTable updateTable);
	/**
	 * 创建表
	 * @param createTable
	 * @return
	 */
	public int create(CreateTable createTable);
	
	/**
	 * 添加索引
	 * @param indexTable
	 * @return
	 */
	public int addIndex(IndexTable indexTable);
	/**
	 * 删除表
	 * @param tableName
	 * @return
	 */
	public int drop(String tableName);

}
