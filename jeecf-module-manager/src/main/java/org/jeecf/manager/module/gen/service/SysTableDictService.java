package org.jeecf.manager.module.gen.service;

import org.jeecf.manager.common.service.NamespaceAndDbAuthService;
import org.jeecf.manager.module.gen.dao.SysTableDictDao;
import org.jeecf.manager.module.gen.model.domian.SysTableDict;
import org.jeecf.manager.module.gen.model.po.SysTableDictPO;
import org.jeecf.manager.module.gen.model.query.SysTableDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTableDictResult;
import org.springframework.stereotype.Service;

/**
 * 数据字典 service
 * 
 * @author GloryJian
 *
 */
@Service
public class SysTableDictService  extends NamespaceAndDbAuthService<SysTableDictDao, SysTableDictPO,SysTableDictResult,SysTableDictQuery, SysTableDict>{

}
