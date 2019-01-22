package org.jeecf.manager.module.extend.facade;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.ResponseUtils;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTable;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTableColumn;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableResult;
import org.jeecf.manager.module.extend.service.SysVirtualTableColumnService;
import org.jeecf.manager.module.extend.service.SysVirtualTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 虚表 facade
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class SysVirtualTableFacade {

    @Autowired
    private SysVirtualTableService sysVirtualTableService;

    @Autowired
    private SysVirtualTableColumnService sysVirtualTableColumnService;

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<SysVirtualTableResult> save(SysVirtualTable sysVirtualTable) {
        Response<SysVirtualTableResult> sysVirtualTableRes = sysVirtualTableService.saveByAuth(sysVirtualTable);
        List<SysVirtualTableColumn> sysVirtualTableColumnList = sysVirtualTable.getSysVirtualTableColumns();
        if (CollectionUtils.isNotEmpty(sysVirtualTableColumnList)) {
            if (StringUtils.isNotEmpty(sysVirtualTable.getId())) {
                SysVirtualTableColumn sysVirtualTableColumn = new SysVirtualTableColumn();
                sysVirtualTableColumn.setSysVirtualTableId(Integer.valueOf(sysVirtualTable.getId()));
                sysVirtualTableColumnService.delete(sysVirtualTableColumn);
            }
            sysVirtualTableColumnList.forEach(sysVirtualTableColumn -> {
                sysVirtualTableColumn.setNewRecord(true);
                sysVirtualTableColumn.setSysVirtualTableId(Integer.valueOf(sysVirtualTable.getId()));
                sysVirtualTableColumnService.save(sysVirtualTableColumn);
            });
        }
        return sysVirtualTableRes;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> delete(SysVirtualTable sysVirtualTable) {
        Response<Integer> genTableRes = sysVirtualTableService.deleteByAuth(sysVirtualTable);
        if (ResponseUtils.isNotEmpty(genTableRes)) {
            SysVirtualTableColumn deleteTableColumn = new SysVirtualTableColumn();
            deleteTableColumn.setSysVirtualTableId(Integer.valueOf(sysVirtualTable.getId()));
            sysVirtualTableColumnService.delete(deleteTableColumn);
        }
        return genTableRes;
    }

}
