package org.jeecf.manager.proxy;

import java.util.List;

import org.jeecf.common.model.Response;
import org.jeecf.manager.annotation.TargetDataSource;
import org.jeecf.manager.common.utils.PhysicalTableUtils;
import org.jeecf.manager.engine.model.create.CreateTable;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.model.schema.SchemaTable;
import org.jeecf.manager.engine.model.schema.SchemaTableColumn;
import org.jeecf.manager.engine.service.BusinessTableService;
import org.jeecf.manager.engine.service.SchemaTableService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 目标数据源表操作
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class TargetTableProxy {

    @Autowired
    private SchemaTableService schemaTableService;

    @Autowired
    private BusinessTableService businessTableService;

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<List<SchemaTable>> findTableList(SysNamespace sysNamespace) {
        return new Response<List<SchemaTable>>(true, PhysicalTableUtils.filter(schemaTableService.findTableList().getData(), sysNamespace));
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<SchemaTable> getTable(String name) {
        return schemaTableService.getTable(name);
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<List<SchemaTableColumn>> findTableColumn(String tableName) {
        return new Response<>(schemaTableService.findTableColumn(tableName).getData());
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<String> selectTable(SelectTable selectTable) {
        return new Response<>(businessTableService.query(selectTable));
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<Integer> create(CreateTable createTable) {
        return new Response<>(businessTableService.create(createTable));
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @TargetDataSource
    public Response<Integer> drop(String tableName) {
        return new Response<>(businessTableService.drop(tableName));
    }

}
