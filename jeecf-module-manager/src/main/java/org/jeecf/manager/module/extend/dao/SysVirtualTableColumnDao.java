package org.jeecf.manager.module.extend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTableColumn;
import org.jeecf.manager.module.extend.model.po.SysVirtualTableColumnPO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableColumnQuery;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableColumnResult;
/**
 * 虚表字段 dao
 * @author jianyiming
 *
 */
@Mapper
public interface SysVirtualTableColumnDao extends Dao<SysVirtualTableColumnPO,SysVirtualTableColumnResult,SysVirtualTableColumnQuery,SysVirtualTableColumn> {

}
