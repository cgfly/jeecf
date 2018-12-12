package org.jeecf.manager.module.config.facade;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统菜单 门面
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class SysNamespaceFacade {

	@Autowired
	private SysNamespaceService sysNamespaceService;

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> save(SysNamespace sysNamespace) {
		String permission = sysNamespace.getPermission();
		// 如果为插入操作则插入相关权限
		String prefixName = "命名空间";
		PermissionUtils.addPower(sysNamespace.isNewRecord(), prefixName, permission);
		return sysNamespaceService.saveByAuth(sysNamespace);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> delete(SysNamespace sysNamespace) {
		sysNamespace = sysNamespaceService.getByAuth(sysNamespace).getData();
		if (sysNamespace != null) {
			String permission = sysNamespace.getPermission();
			PermissionUtils.deletePower(permission);
			return sysNamespaceService.deleteByAuth(sysNamespace);
		}
		return new Response<>(null);
	}

}
