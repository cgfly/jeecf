package org.jeecf.manager.module.template.dao;


import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.template.model.domain.GenField;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.result.GenFieldResult;

/**
 * 模版参数
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface GenFieldDao extends Dao<GenFieldPO,GenFieldResult,GenFieldQuery,GenField>{


}