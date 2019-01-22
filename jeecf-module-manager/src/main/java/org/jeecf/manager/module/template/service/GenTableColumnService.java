package org.jeecf.manager.module.template.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.template.dao.GenTableColumnDao;
import org.jeecf.manager.module.template.model.domain.GenTableColumn;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.springframework.stereotype.Service;

/**
 * 业务表列表service
 * 
 * @author jianyiming
 *
 */
@Service
public class GenTableColumnService extends BaseService<GenTableColumnDao, GenTableColumnPO, GenTableColumnResult, GenTableColumnQuery, GenTableColumn> {

}
