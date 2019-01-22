package org.jeecf.manager.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.engine.model.schema.SchemaTable;
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
                if (splitName.length > 1) {
                    if (splitName[1].equals(sysNamespace.getName())) {
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

    public static List<SchemaTable> filter(List<SchemaTable> schemaTableList, SysNamespace sysNamespace) {
        List<SchemaTable> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(schemaTableList)) {
            schemaTableList.forEach(schemaTable -> {
                String[] splitName = schemaTable.getName().split(SPLIT_MATCH);
                if (splitName.length > 1) {
                    if (splitName[1].equals(sysNamespace.getName())) {
                        resultList.add(schemaTable);
                    }
                } else {
                    resultList.add(schemaTable);
                }
            });
        }
        return resultList;
    }
}
