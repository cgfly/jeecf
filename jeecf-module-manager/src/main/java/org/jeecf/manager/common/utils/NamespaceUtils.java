package org.jeecf.manager.common.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
/**
 * 命名空间工具类
 * @author jianyiming
 *
 */
public class NamespaceUtils {
	
	private static SysUserNamespaceService sysUserNamespaceService = SpringContextUtils.getBean("sysUserNamespaceService",SysUserNamespaceService.class);
	
	private static SysNamespaceService sysNamespaceService = SpringContextUtils.getBean("sysNamespaceService",SysNamespaceService.class);
	/**
	 * 获取当前命名空间 标识
	 * @return
	 */
	public static Integer getNamespaceId() {
	    String userId =	UserUtils.getCurrentUserId();
	    SysUserNamespaceQuery sysUserNamespace = new  SysUserNamespaceQuery();
	    sysUserNamespace.setUserId(userId);
	    List<SysUserNamespaceResult> namespaceList = sysUserNamespaceService.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
	    if(CollectionUtils.isNotEmpty(namespaceList)) {
	    	return namespaceList.get(0).getNamespaceId();
	    }
	    return null;
	}
	
	/**
	 * 获取当前命名空间 标识
	 * @return
	 */
	public static Integer getNamespaceId(String userId) {
	    SysUserNamespaceQuery sysUserNamespace = new  SysUserNamespaceQuery();
	    sysUserNamespace.setUserId(userId);
	    List<SysUserNamespaceResult> namespaceList = sysUserNamespaceService.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
	    if(CollectionUtils.isNotEmpty(namespaceList)) {
	    	return namespaceList.get(0).getNamespaceId();
	    }
	    return null;
	}
	
	/**
	 * 获取当前命名空间
	 * @return
	 */
	public static SysNamespace getNamespace(String userId) {
	    SysUserNamespaceQuery sysUserNamespace = new  SysUserNamespaceQuery();
	    sysUserNamespace.setUserId(userId);
	    List<SysUserNamespaceResult> namespaceList = sysUserNamespaceService.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
	    if(CollectionUtils.isNotEmpty(namespaceList)) {
	    	SysUserNamespace sysUsernameSpace = namespaceList.get(0);
	    	SysNamespace querySysNamespace = new SysNamespace();
	    	querySysNamespace.setId(String.valueOf(sysUsernameSpace.getNamespaceId()));
	    	return sysNamespaceService.getByAuth(querySysNamespace).getData();
	    }
	    return null;
	}
	
	/**
	 * 获取当前命名空间 标识
	 * @return
	 */
	public static void initSysNamespace() {
		String userId =	UserUtils.getCurrentUserId();
		Integer namespaceId = NamespaceUtils.getNamespaceId(userId);
		if(namespaceId == null || namespaceId <= 0) {
			List<SysNamespaceResult> sysNamespaceList = sysNamespaceService.findListByAuth(new SysNamespacePO(new SysNamespaceQuery())).getData();
			System.out.println(sysNamespaceList.size());
			if(CollectionUtils.isNotEmpty(sysNamespaceList)) {
				List<SysNamespaceResult>  filterNamespaceList =	PermissionUtils.filter(sysNamespaceList);
				if(CollectionUtils.isNotEmpty(filterNamespaceList)) {
					SysUserNamespace sysUserNamespace = new SysUserNamespace();
					sysUserNamespace.setUserId(userId);
					sysUserNamespace.setNamespaceId(Integer.valueOf(filterNamespaceList.get(0).getId()));
					sysUserNamespaceService.save(sysUserNamespace);
				}
			}
			
		}
	}
}
