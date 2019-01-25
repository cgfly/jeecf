package org.jeecf.manager.engine.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.engine.mysql.utils.JniValidate;
import org.jeecf.manager.engine.dao.SchemaTableDao;
import org.jeecf.manager.engine.model.schema.SchemaTable;
import org.jeecf.manager.engine.model.schema.SchemaTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物理表 service
 * 
 * @author jianyiming
 *
 */
@Service
public class SchemaTableService {

    @Autowired
    private SchemaTableDao schemaTableDao;

    public Response<SchemaTable> getTable(String name) {
        SchemaTable schemaTable = new SchemaTable();
        schemaTable.setName(JniValidate.columnValidate(name));
        List<SchemaTable> schemaTableList = schemaTableDao.findTableList(schemaTable);
        if (CollectionUtils.isNotEmpty(schemaTableList)) {
            return new Response<>(schemaTableList.get(0));
        }
        return new Response<>();
    }

    public Response<List<SchemaTable>> findTableList() {
        return new Response<>(schemaTableDao.findTableList(new SchemaTable()));
    }

    public Response<List<SchemaTableColumn>> findTableColumn(String tableName) {
        SchemaTableColumn schemaTableColumn = new SchemaTableColumn();
        schemaTableColumn.setTableName(JniValidate.columnValidate(tableName));
        return new Response<>(schemaTableDao.findTableColumnList(schemaTableColumn));
    }

    public Response<List<SchemaTableColumn>> findTableColumnList() {
        return new Response<>(schemaTableDao.findTableColumnList(new SchemaTableColumn()));
    }

}
