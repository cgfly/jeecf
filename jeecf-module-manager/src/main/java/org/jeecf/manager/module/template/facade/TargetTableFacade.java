package org.jeecf.manager.module.template.facade;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.model.Response;
import org.jeecf.manager.annotation.TargetDataSource;
import org.jeecf.manager.common.utils.PhysicalTableUtils;
import org.jeecf.manager.engine.model.SchemaTable;
import org.jeecf.manager.engine.model.SchemaTableColumn;
import org.jeecf.manager.engine.service.SchemaTableService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.template.model.domain.GenTableColumn;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class TargetTableFacade {
	

	@Autowired
	private SchemaTableService schemaTableService;
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@TargetDataSource
	public Response<List<SchemaTable>> findTableList(SysNamespace sysNamespace) {
		return new Response<List<SchemaTable>>(true,
				PhysicalTableUtils.filter(schemaTableService.findTableList().getData(), sysNamespace));
	}
	
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@TargetDataSource
	public Response<SchemaTable> getTable(String name,SysNamespace sysNamespace) {
		return schemaTableService.getTable(name);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@TargetDataSource
	public Response<List<GenTableColumnResult>> findTableColumn(GenTableColumn genTableColumn) {
		List<GenTableColumnResult> genTableColumnList = new ArrayList<>();
		SchemaTableColumn physicalTableColumn = new SchemaTableColumn();
		BeanUtils.copyProperties(genTableColumn, physicalTableColumn);
		physicalTableColumn.setTableName(genTableColumn.getGenTable().getName());
		List<SchemaTableColumn> physicalTableColumnList = schemaTableService
				.findTableColumn(physicalTableColumn.getTableName()).getData();
		physicalTableColumnList.forEach(column -> {
			GenTableColumnResult result = new GenTableColumnResult();
			BeanUtils.copyProperties(column, result);
			genTableColumnList.add(result);
		});
		return new Response<>(genTableColumnList);
	}

}
