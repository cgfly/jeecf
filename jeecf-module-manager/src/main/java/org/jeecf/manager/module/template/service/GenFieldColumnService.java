package org.jeecf.manager.module.template.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.template.dao.GenFieldColumnDao;
import org.jeecf.manager.module.template.model.domain.GenFieldColumn;
import org.jeecf.manager.module.template.model.po.GenFieldColumnPO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.result.GenFieldColumnResult;
import org.springframework.stereotype.Service;

/**
 * 模版参数列表
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class GenFieldColumnService extends BaseService<GenFieldColumnDao, GenFieldColumnPO, GenFieldColumnResult, GenFieldColumnQuery, GenFieldColumn> {

}