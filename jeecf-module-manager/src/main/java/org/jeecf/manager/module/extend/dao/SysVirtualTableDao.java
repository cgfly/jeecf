package org.jeecf.manager.module.extend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTable;
import org.jeecf.manager.module.extend.model.po.SysVirtualTablePO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableQuery;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableResult;

/**
 * 虚表 dao
 * 
 * @author jianyiming
 * 
 */
@Mapper
public interface SysVirtualTableDao extends Dao<SysVirtualTablePO, SysVirtualTableResult, SysVirtualTableQuery, SysVirtualTable> {

}
