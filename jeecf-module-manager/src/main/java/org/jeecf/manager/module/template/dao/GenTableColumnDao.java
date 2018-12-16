package org.jeecf.manager.module.template.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.template.model.domain.GenTableColumn;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
/**
 * 业务表字段dao
 * @author jianyiming
 *
 */
@Mapper
public interface GenTableColumnDao extends Dao<GenTableColumnPO,GenTableColumnResult,GenTableColumnQuery,GenTableColumn>{

	
}
