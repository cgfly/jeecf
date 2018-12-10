package org.jeecf.manager.common.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jeecf.manager.module.config.model.result.SysOfficeResult;
import org.jeecf.manager.module.config.service.SysOfficeService;
/**
 * 组织结构工具类
 * @author jianyiming
 *
 */
public class OfficeUtils {
	
    private static SysOfficeService sysOfficeService = SpringContextUtils.getBean("sysOfficeService", SysOfficeService.class);
	
	public static Set<String> findChilds(String userId) {
		Set<String> result = new HashSet<>();
		List<SysOfficeResult> sysOfficeList = sysOfficeService.findChilds(userId).getData();
		sysOfficeList.forEach(sysOffice->{
			result.add(sysOffice.getId());
		});
		return result;
	}

}
