package org.jeecf.manager.module.template.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableResult;
/**
 * 业务表dao
 * @author jianyiming
 *
 */
@Mapper
public interface GenTableDao extends Dao<GenTablePO,GenTableResult,GenTableQuery,GenTable>{
	

}
