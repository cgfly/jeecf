package org.jeecf.manager.module.gen.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.gen.model.domian.SysTreeDict;
import org.jeecf.manager.module.gen.model.po.SysTreeDictPO;
import org.jeecf.manager.module.gen.model.query.SysTreeDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTreeDictResult;

/**
 * 树字典 DAO
 * 
 * @author jianyiming
 *
 */
@Mapper
public interface SysTreeDictDao extends Dao<SysTreeDictPO, SysTreeDictResult, SysTreeDictQuery, SysTreeDict> {

}
