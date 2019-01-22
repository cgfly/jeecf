package org.jeecf.manager.module.gen.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.gen.model.domian.SysTableDict;
import org.jeecf.manager.module.gen.model.po.SysTableDictPO;
import org.jeecf.manager.module.gen.model.query.SysTableDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTableDictResult;

/**
 * 表组字典 dao
 * 
 * @author jianyiming
 *
 */
@Mapper
public interface SysTableDictDao extends Dao<SysTableDictPO, SysTableDictResult, SysTableDictQuery, SysTableDict> {

}
