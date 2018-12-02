package org.jeecf.manager.module.template.service;

import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.module.template.dao.GenTableDao;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.springframework.stereotype.Service;
/**
 * 业务表service
 * @author jianyiming
 *
 */
@Service
public class GenTableService  extends NamespaceAuthService<GenTableDao,GenTablePO,GenTableResult,GenTableQuery,GenTable>{
	
}
