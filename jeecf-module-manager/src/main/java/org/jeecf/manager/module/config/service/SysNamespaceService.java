package org.jeecf.manager.module.config.service;

import org.jeecf.manager.common.service.PermissionAuthService;
import org.jeecf.manager.module.config.dao.SysNamespaceDao;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.springframework.stereotype.Service;

/**
 * 系统命名空间
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysNamespaceService extends PermissionAuthService<SysNamespaceDao, SysNamespacePO, SysNamespaceResult, SysNamespaceQuery, SysNamespace> {

}