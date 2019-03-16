package org.jeecf.manager.common.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.module.config.model.result.SysOfficeResult;
import org.jeecf.manager.module.config.service.SysOfficeService;

/**
 * 组织结构工具类
 * 
 * @author jianyiming
 *
 */
public class OfficeUtils {

    private static SysOfficeService SYS_OFFICE_SERVICE = SpringContextUtils.getBean("sysOfficeService", SysOfficeService.class);

    public static Set<String> findChilds(String officeId) {
        Set<String> result = new HashSet<>();
        List<SysOfficeResult> sysOfficeList = SYS_OFFICE_SERVICE.findChilds(officeId).getData();
        if (CollectionUtils.isNotEmpty(sysOfficeList)) {
            sysOfficeList.forEach(sysOffice -> {
                result.add(sysOffice.getId());
            });
        }
        return result;
    }

}
