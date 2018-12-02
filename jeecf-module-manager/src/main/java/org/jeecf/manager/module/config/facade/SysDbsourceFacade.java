package org.jeecf.manager.module.config.facade;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.service.SysDbsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统数据连接源门面
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class SysDbsourceFacade {

	@Autowired
	private SysDbsourceService sysDbsourceService;

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> save(SysDbsource sysDbsource) {
		String permission = sysDbsource.getPermission();
		String prefixName = "数据连接源";
		PermissionUtils.addPower(sysDbsource.isNewRecord(), prefixName, permission);
		return sysDbsourceService.saveByAuth(sysDbsource);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> delete(SysDbsource sysDbsource) {
		sysDbsource = sysDbsourceService.getByAuth(sysDbsource).getData();
		if (sysDbsource != null) {
			String permission = sysDbsource.getPermission();
			PermissionUtils.deletePower(permission);
			return sysDbsourceService.delete(sysDbsource);
		}
		return new Response<>(null);
	}

}
