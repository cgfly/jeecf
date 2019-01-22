package org.jeecf.manager.module.operation.service;

import org.jeecf.manager.common.service.UserAuthService;
import org.jeecf.manager.module.operation.dao.CustomerActionLogDao;
import org.jeecf.manager.module.operation.model.domain.CustomerActionLog;
import org.jeecf.manager.module.operation.model.po.CustomerActionLogPO;
import org.jeecf.manager.module.operation.model.query.CustomerActionLogQuery;
import org.jeecf.manager.module.operation.model.result.CustomerActionLogResult;
import org.springframework.stereotype.Service;

/**
 * 客户操作日志 service
 * 
 * @author jianyiming
 *
 */
@Service
public class CustomerActionLogService extends UserAuthService<CustomerActionLogDao, CustomerActionLogPO, CustomerActionLogResult, CustomerActionLogQuery, CustomerActionLog> {

}
