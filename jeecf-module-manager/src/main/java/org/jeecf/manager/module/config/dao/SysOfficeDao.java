package org.jeecf.manager.module.config.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.config.model.domain.SysOffice;
import org.jeecf.manager.module.config.model.po.SysOfficePO;
import org.jeecf.manager.module.config.model.query.SysOfficeQuery;
import org.jeecf.manager.module.config.model.result.SysOfficeResult;

/**
 * 组织机构 dao
 * @author jianyiming
 *
 */
@Mapper
public interface SysOfficeDao extends Dao<SysOfficePO,SysOfficeResult,SysOfficeQuery,SysOffice>{

}
