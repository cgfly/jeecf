package org.jeecf.manager.module.config.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.service.PermissionAuthService;
import org.jeecf.manager.common.utils.NamespaceUtils;
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

    public SysNamespace get(String userId, String namespaceName) {
        SysNamespace sysNamespace = null;
        if (StringUtils.isEmpty(namespaceName)) {
            sysNamespace = NamespaceUtils.getNamespace(userId);
        } else {
            SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
            sysNamespaceQuery.setName(namespaceName);
            SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
            Response<List<SysNamespaceResult>> sysNamespaceResultListRes = super.findList(sysNamespacePO);
            if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
                sysNamespace = sysNamespaceResultListRes.getData().get(0);
            }
        }
        return sysNamespace;
    }

}