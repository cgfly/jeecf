package org.jeecf.manager.engine;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.manager.Application;
import org.jeecf.manager.engine.model.SelectTable;
import org.jeecf.manager.engine.model.SelectTableColumn;
import org.jeecf.manager.engine.service.BusinessTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 表测试
 * @author jianyiming
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessTableServiceTest {
	
	@Autowired
	private BusinessTableService businessTableService;
	
	@Test
	public void queryAll(){
		List<SelectTableColumn> columnList = new ArrayList<>();
		SelectTable selectTable = new SelectTable();
		selectTable.setName("genField");
		selectTable.setTableName("gen_field");
		SelectTableColumn columnId = new SelectTableColumn();
		columnId.setColumnName("id");
		columnId.setName("id");
		SelectTableColumn columnNamespaceId = new SelectTableColumn();
		columnNamespaceId.setColumnName("sys_namespace_id");
		columnNamespaceId.setName("sysNamespaceId");
		columnList.add(columnId);
		columnList.add(columnNamespaceId);
		selectTable.setColumnList(columnList);
		String result = businessTableService.queryAll(selectTable);
		System.out.println(result);
	}

}