package org.jeecf.manager.module.gen.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.gen.model.domian.SysDict;
import org.jeecf.manager.module.gen.model.po.SysDictPO;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.result.SysDictResult;

/**
 * 数据字典
 * 
 * @author GloryJian
 *
 */
@Mapper
public interface SysDictDao extends Dao<SysDictPO, SysDictResult, SysDictQuery, SysDict> {

}