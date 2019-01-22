package org.jeecf.manager.module.operation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.operation.model.domain.CustomerActionLog;
import org.jeecf.manager.module.operation.model.po.CustomerActionLogPO;
import org.jeecf.manager.module.operation.model.query.CustomerActionLogQuery;
import org.jeecf.manager.module.operation.model.result.CustomerActionLogResult;

/**
 * 客户操作日志 dao
 * 
 * @author jianyiming
 *
 */
@Mapper
public interface CustomerActionLogDao extends Dao<CustomerActionLogPO, CustomerActionLogResult, CustomerActionLogQuery, CustomerActionLog> {

}