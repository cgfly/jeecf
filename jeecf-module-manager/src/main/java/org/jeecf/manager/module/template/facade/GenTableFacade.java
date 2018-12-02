package org.jeecf.manager.module.template.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.ResponseUtils;
import org.jeecf.manager.annotation.TargetDataSource;
import org.jeecf.manager.common.utils.PhysicalTableUtils;
import org.jeecf.manager.engine.model.PhysicalTable;
import org.jeecf.manager.engine.model.PhysicalTableColumn;
import org.jeecf.manager.engine.service.PhysicalTableService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.domain.GenTableColumn;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.service.GenTableColumnService;
import org.jeecf.manager.module.template.service.GenTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * genTable 门面
 * 
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class GenTableFacade {

	@Autowired
	private GenTableColumnService genTableColumnService;

	@Autowired
	private GenTableService genTableService;

	@Autowired
	private PhysicalTableService physicalTableService;

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> saveTable(GenTable genTable) {
		GenTableQuery queryTable = new GenTableQuery();
		queryTable.setName(genTable.getName());
		List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryTable)).getData();
		if (CollectionUtils.isNotEmpty(genTableList)) {
			genTable.setId(genTableList.get(0).getId());
		}
		genTableService.saveByAuth(genTable);
		List<GenTableColumnResult> columnList = genTable.getGenTableColumns();
		if (CollectionUtils.isNotEmpty(columnList)) {
			for (GenTableColumn column : columnList) {
				column.setGenTable(genTable);
				genTableColumnService.save(column);
			}
		}
		return new Response<Integer>(1);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> deleteTable(GenTable genTable) {
		Response<Integer> genTableRes = genTableService.deleteByAuth(genTable);
		if (ResponseUtils.isNotEmpty(genTableRes)) {
			GenTableColumn deleteTableColumn = new GenTableColumn();
			deleteTableColumn.setGenTable(genTable);
			genTableColumnService.delete(deleteTableColumn);
		}
		return new Response<Integer>(true);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<GenTable> findParentTable(String parentId) {
		GenTable parentTable = null;
		if (StringUtils.isNotEmpty(parentId)) {
			GenTable queryTable = new GenTable();
			queryTable.setId(parentId);
			parentTable = this.genTableService.get(queryTable).getData();
			if (parentTable != null) {
				GenTableColumnQuery queryGenTableColumn = new GenTableColumnQuery();
				queryGenTableColumn.setGenTable(parentTable);
				List<GenTableColumnResult> genTableColumnResultList = genTableColumnService
						.findList(new GenTableColumnPO(queryGenTableColumn)).getData();
				parentTable.setGenTableColumns(genTableColumnResultList);
			}
		}
		return new Response<>(parentTable);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<List<GenTableResult>> findChildTables(String id) {
		GenTableQuery queryGenTable = new GenTableQuery();
		queryGenTable.setParentTableId(id);
		List<GenTableResult> childTableList = this.genTableService.findList(new GenTablePO(queryGenTable)).getData();
		if (CollectionUtils.isNotEmpty(childTableList)) {
			childTableList.forEach(tableResult -> {
				GenTableColumnQuery queryGenTableColumn = new GenTableColumnQuery();
				queryGenTableColumn.setGenTable(tableResult);
				List<GenTableColumnResult> genTableColumnResultList = genTableColumnService
						.findList(new GenTableColumnPO(queryGenTableColumn)).getData();
				tableResult.setGenTableColumns(genTableColumnResultList);
			});
		}
		return new Response<>(childTableList);
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@TargetDataSource
	public Response<List<PhysicalTable>> findTableList(GenTable genTable, SysNamespace sysNamespace) {
		PhysicalTable physicalTable = new PhysicalTable();
		BeanUtils.copyProperties(genTable, physicalTable);
		return new Response<List<PhysicalTable>>(true,
				PhysicalTableUtils.filter(physicalTableService.findTableList(physicalTable).getData(), sysNamespace));
	}

	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@TargetDataSource
	public Response<List<GenTableColumnResult>> findTableColumnList(GenTableColumn genTableColumn) {
		List<GenTableColumnResult> genTableColumnList = new ArrayList<>();
		PhysicalTableColumn physicalTableColumn = new PhysicalTableColumn();
		BeanUtils.copyProperties(genTableColumn, physicalTableColumn);
		physicalTableColumn.setTableName(genTableColumn.getGenTable().getName());
		List<PhysicalTableColumn> physicalTableColumnList = physicalTableService
				.findTableColumnList(physicalTableColumn).getData();
		physicalTableColumnList.forEach(column -> {
			GenTableColumnResult result = new GenTableColumnResult();
			BeanUtils.copyProperties(column, result);
			genTableColumnList.add(result);
		});
		return new Response<>(genTableColumnList);
	}
}
