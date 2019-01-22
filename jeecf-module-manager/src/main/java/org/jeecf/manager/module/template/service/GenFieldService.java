package org.jeecf.manager.module.template.service;

import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.module.template.dao.GenFieldDao;
import org.jeecf.manager.module.template.model.domain.GenField;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.springframework.stereotype.Service;

/**
 * 模版参数
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class GenFieldService extends NamespaceAuthService<GenFieldDao, GenFieldPO, GenFieldResult, GenFieldQuery, GenField> {

}