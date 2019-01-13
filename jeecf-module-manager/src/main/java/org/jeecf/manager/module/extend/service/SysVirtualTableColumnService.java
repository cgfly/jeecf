package org.jeecf.manager.module.extend.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.extend.dao.SysVirtualTableColumnDao;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTableColumn;
import org.jeecf.manager.module.extend.model.po.SysVirtualTableColumnPO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableColumnQuery;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableColumnResult;
import org.springframework.stereotype.Service;
/**
 * 虚表字段service
 * @author jianyiming
 *
 */
@Service
public class SysVirtualTableColumnService extends BaseService<SysVirtualTableColumnDao,SysVirtualTableColumnPO,SysVirtualTableColumnResult,SysVirtualTableColumnQuery,SysVirtualTableColumn> {

}
