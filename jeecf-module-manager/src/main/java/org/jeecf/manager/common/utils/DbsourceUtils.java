package org.jeecf.manager.common.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.properties.ThreadLocalProperties;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;
import org.jeecf.manager.module.config.service.SysDbsourceService;

public class DbsourceUtils {

	private static ThreadLocalProperties threadLocalProperties = SpringContextUtils.getBean("threadLocalProperties",
			ThreadLocalProperties.class);

	private static SysDbsourceService sysDbsourceService = SpringContextUtils.getBean("sysDbsourceService",
			SysDbsourceService.class);

	private static final String DBSOURCE_ID = "dbsourceId";

	public static Integer getSysDbsourceId() {
		String dbsourceId = threadLocalProperties.get(DBSOURCE_ID);
		if (StringUtils.isEmpty(dbsourceId)) {
			SysDbsourceQuery sysDbsourceQuery = new SysDbsourceQuery();
			sysDbsourceQuery.setKeyName(DynamicDataSourceContextHolder.getCurrentDataSourceValue());
			SysDbsourcePO sysDbsourcePO = new SysDbsourcePO(sysDbsourceQuery);
			List<SysDbsourceResult> dbList = sysDbsourceService.findListByAuth(sysDbsourcePO).getData();
			if (CollectionUtils.isNotEmpty(dbList)) {
				dbsourceId = String.valueOf(dbList.get(0).getId());
				threadLocalProperties.set(DBSOURCE_ID, dbsourceId);
				return Integer.valueOf(dbsourceId);
			}
		}
        return Integer.valueOf(dbsourceId);
	}

}
