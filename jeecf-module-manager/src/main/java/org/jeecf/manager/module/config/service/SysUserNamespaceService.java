package org.jeecf.manager.module.config.service;

import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.config.dao.SysUserNamespaceDao;
import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
import org.jeecf.manager.module.config.model.po.SysUserNamespacePO;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysUserNamespaceResult;
import org.springframework.stereotype.Service;

/**
 * 用户命名空间对应
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysUserNamespaceService extends BaseService<SysUserNamespaceDao,SysUserNamespacePO,SysUserNamespaceResult,SysUserNamespaceQuery,SysUserNamespace> {
   	
}