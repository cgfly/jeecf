package org.jeecf.manager.module.template.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.template.model.domain.GenFieldColumn;
import org.jeecf.manager.module.template.model.po.GenFieldColumnPO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.result.GenFieldColumnResult;

/**
 * 模版参数列表
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface GenFieldColumnDao extends Dao<GenFieldColumnPO, GenFieldColumnResult, GenFieldColumnQuery, GenFieldColumn> {

}