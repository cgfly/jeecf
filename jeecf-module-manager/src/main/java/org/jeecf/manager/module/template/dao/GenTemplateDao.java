package org.jeecf.manager.module.template.dao;


import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.manager.module.template.model.po.GenTemplatePO;
import org.jeecf.manager.module.template.model.query.GenTemplateQuery;
import org.jeecf.manager.module.template.model.result.GenTemplateResult;

/**
 * 模版配置
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface GenTemplateDao extends Dao<GenTemplatePO,GenTemplateResult,GenTemplateQuery,GenTemplate>{


}