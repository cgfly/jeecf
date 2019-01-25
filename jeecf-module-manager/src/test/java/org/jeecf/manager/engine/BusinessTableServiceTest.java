package org.jeecf.manager.engine;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.engine.mysql.model.create.CreateTable;
import org.jeecf.engine.mysql.model.create.CreateTableColumn;
import org.jeecf.engine.mysql.model.query.SelectTable;
import org.jeecf.engine.mysql.model.query.SelectTableColumn;
import org.jeecf.manager.Application;
import org.jeecf.manager.engine.service.BusinessTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 表测试
 * 
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessTableServiceTest {

    @Autowired
    private BusinessTableService businessTableService;

    @Test
    public void query() {
        List<SelectTableColumn> columnList = new ArrayList<>();
        SelectTable selectTable = SelectTable.Builder.build("genField", "gen_field");
        columnList.add(SelectTableColumn.Builder.build("id", "id"));
        columnList.add(SelectTableColumn.Builder.build("sysNamespaceId", "sys_namespace_id"));
        selectTable.setSelectTableColumns(columnList);
        businessTableService.query(selectTable);
    }

    @Test
    public void create() {
        List<CreateTableColumn> createTableColumns = new ArrayList<>();
        CreateTableColumn createTableColumn = CreateTableColumn.builder().setColumnName("test").setType("int(11)").isPrimaryKey(true).setComment("0").build();
        createTableColumns.add(createTableColumn);
        CreateTable createTable = CreateTable.builder().setTableName("test").setComment("测试").addCreateTableColumns(createTableColumns).build();
        businessTableService.create(createTable);
    }

}
