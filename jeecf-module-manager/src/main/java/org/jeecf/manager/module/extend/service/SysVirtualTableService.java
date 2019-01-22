package org.jeecf.manager.module.extend.service;

import org.jeecf.manager.common.service.NamespaceAndDbAuthService;
import org.jeecf.manager.module.extend.dao.SysVirtualTableDao;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTable;
import org.jeecf.manager.module.extend.model.po.SysVirtualTablePO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableQuery;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableResult;
import org.springframework.stereotype.Service;

/**
 * 虚表 service
 * 
 * @author jianyiming
 *
 */
@Service
public class SysVirtualTableService extends NamespaceAndDbAuthService<SysVirtualTableDao, SysVirtualTablePO, SysVirtualTableResult, SysVirtualTableQuery, SysVirtualTable> {

}
