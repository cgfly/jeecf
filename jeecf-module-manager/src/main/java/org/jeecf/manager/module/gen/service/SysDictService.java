package org.jeecf.manager.module.gen.service;

import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.module.gen.dao.SysDictDao;
import org.jeecf.manager.module.gen.model.domian.SysDict;
import org.jeecf.manager.module.gen.model.po.SysDictPO;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.result.SysDictResult;
import org.springframework.stereotype.Service;

/**
 * 数据字典
 * 
 * @author GloryJian
 *
 */
@Service
public class SysDictService extends NamespaceAuthService<SysDictDao, SysDictPO, SysDictResult, SysDictQuery, SysDict> {

}