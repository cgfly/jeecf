package org.jeecf.manager.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.engine.model.PhysicalTable;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.template.model.result.GenTableResult;

/**
 * 物理表工具类
 * 
 * @author jianyiming
 *
 */
public class PhysicalTableUtils {

	private static final String SPLIT_MATCH = "_#";

	/**
	 * 通过命名空间过滤表
	 */
	public static List<GenTableResult> filter(List<GenTableResult> genTableList) {
		List<GenTableResult> resultList = new ArrayList<>();
		SysNamespace sysNamespace = NamespaceUtils.getNamespace(UserUtils.getCurrentUserId());
		if (CollectionUtils.isNotEmpty(genTableList)) {
			genTableList.forEach(genTable -> {
				String[] splitName = genTable.getName().split(SPLIT_MATCH);
                if(splitName.length > 1) {
                	if(splitName[1].equals(sysNamespace.getName())) {
                		genTable.setName(splitName[0]);
                		resultList.add(genTable);
                	}
                } else {
                	resultList.add(genTable);
                }
			});
		}
		return resultList;
	}
	
	public static List<PhysicalTable> filter(List<PhysicalTable> physicalTableList,SysNamespace sysNamespace) {
		List<PhysicalTable> resultList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(physicalTableList)) {
			physicalTableList.forEach(physicalTable -> {
				String[] splitName = physicalTable.getName().split(SPLIT_MATCH);
                if(splitName.length > 1) {
                	if(splitName[1].equals(sysNamespace.getName())) {
                		physicalTable.setName(physicalTable.getName());
                		resultList.add(physicalTable);
                	}
                } else {
                	resultList.add(physicalTable);
                }
			});
		}
		return resultList;
	}
}
