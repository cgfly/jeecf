package org.jeecf.manager.common.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.common.properties.ThreadLocalProperties;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.po.SysUserNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.model.result.SysUserNamespaceResult;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.config.service.SysUserNamespaceService;

import com.alibaba.druid.util.StringUtils;

/**
 * 命名空间工具类
 * 
 * @author jianyiming
 *
 */
public class NamespaceUtils {

    private static final SysUserNamespaceService SYS_USER_NAMESPACE_SERVICE = SpringContextUtils.getBean("sysUserNamespaceService", SysUserNamespaceService.class);

    private static final SysNamespaceService SYS_NAMESPACE_SERVICE = SpringContextUtils.getBean("sysNamespaceService", SysNamespaceService.class);

    private static final ThreadLocalProperties THREAD_LOCAL_PROPERTIES = SpringContextUtils.getBean("threadLocalProperties", ThreadLocalProperties.class);

    public static final String NAMESPACE_ID = "namespaceId";

    /**
     * 获取当前命名空间 标识
     * 
     * @return
     */
    public static Integer getNamespaceId() {
        String namespaceId = THREAD_LOCAL_PROPERTIES.get(NAMESPACE_ID);
        if (StringUtils.isEmpty(namespaceId)) {
            String userId = UserUtils.getCurrentUserId();
            SysUserNamespaceQuery sysUserNamespace = new SysUserNamespaceQuery();
            sysUserNamespace.setUserId(userId);
            List<SysUserNamespaceResult> namespaceList = SYS_USER_NAMESPACE_SERVICE.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
            if (CollectionUtils.isNotEmpty(namespaceList)) {
                namespaceId = String.valueOf(namespaceList.get(0).getNamespaceId());
                THREAD_LOCAL_PROPERTIES.set(NAMESPACE_ID, namespaceId);
                return Integer.valueOf(namespaceId);
            }
            return null;
        }
        return Integer.valueOf(namespaceId);
    }

    /**
     * 获取当前命名空间 标识
     * 
     * @return
     */
    public static Integer getNamespaceId(String userId) {
        String namespaceId = THREAD_LOCAL_PROPERTIES.get(NAMESPACE_ID);
        if (StringUtils.isEmpty(namespaceId)) {
            SysUserNamespaceQuery sysUserNamespace = new SysUserNamespaceQuery();
            sysUserNamespace.setUserId(userId);
            List<SysUserNamespaceResult> namespaceList = SYS_USER_NAMESPACE_SERVICE.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
            if (CollectionUtils.isNotEmpty(namespaceList)) {
                namespaceId = String.valueOf(namespaceList.get(0).getNamespaceId());
                THREAD_LOCAL_PROPERTIES.set(NAMESPACE_ID, namespaceId);
                return Integer.valueOf(namespaceId);
            }
            return null;
        }
        return Integer.valueOf(namespaceId);
    }

    /**
     * 获取当前命名空间
     * 
     * @return
     */
    public static SysNamespace getNamespace(String userId) {
        SysUserNamespaceQuery sysUserNamespace = new SysUserNamespaceQuery();
        sysUserNamespace.setUserId(userId);
        List<SysUserNamespaceResult> namespaceList = SYS_USER_NAMESPACE_SERVICE.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
        if (CollectionUtils.isNotEmpty(namespaceList)) {
            SysUserNamespace sysUsernameSpace = namespaceList.get(0);
            SysNamespace querySysNamespace = new SysNamespace();
            querySysNamespace.setId(String.valueOf(sysUsernameSpace.getNamespaceId()));
            return SYS_NAMESPACE_SERVICE.getByAuth(querySysNamespace).getData();
        }
        return null;
    }

    /**
     * 获取当前命名空间 标识
     * 
     * @return
     */
    public static void initSysNamespace() {
        String userId = UserUtils.getCurrentUserId();
        Integer namespaceId = NamespaceUtils.getNamespaceId(userId);
        if (namespaceId == null || namespaceId <= 0) {
            List<SysNamespaceResult> sysNamespaceList = SYS_NAMESPACE_SERVICE.findListByAuth(new SysNamespacePO(new SysNamespaceQuery())).getData();
            if (CollectionUtils.isNotEmpty(sysNamespaceList)) {
                List<SysNamespaceResult> filterNamespaceList = PermissionUtils.filter(sysNamespaceList);
                if (CollectionUtils.isNotEmpty(filterNamespaceList)) {
                    SysUserNamespace sysUserNamespace = new SysUserNamespace();
                    sysUserNamespace.setUserId(userId);
                    sysUserNamespace.setNamespaceId(Integer.valueOf(filterNamespaceList.get(0).getId()));
                    SYS_USER_NAMESPACE_SERVICE.save(sysUserNamespace);
                }
            }

        }
    }
}
