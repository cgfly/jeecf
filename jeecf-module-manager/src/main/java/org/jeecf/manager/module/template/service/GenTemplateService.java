package org.jeecf.manager.module.template.service;

import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.module.template.dao.GenTemplateDao;
import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.manager.module.template.model.po.GenTemplatePO;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.result.GenTemplateResult;
import org.springframework.stereotype.Service;

/**
 * 模版配置
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class GenTemplateService extends NamespaceAuthService<GenTemplateDao, GenTemplatePO, GenTemplateResult, GenTemplateQuery, GenTemplate> {

}