package org.jeecf.manager.gen.builder;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.facade.TargetTableFacade;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.service.GenTableColumnService;
import org.jeecf.manager.module.template.service.GenTableService;
/**
 * 语言构建类
 * @author jianyiming
 *
 */
public abstract class LanguageBuilder {
	
	protected static GenTableService genTableService = SpringContextUtils.getBean("genTableService",
			GenTableService.class);
	
	protected static GenTableFacade genTableFacade = SpringContextUtils.getBean("genTableFacade",
			GenTableFacade.class);

	protected static GenTableColumnService genTableColumnService = SpringContextUtils.getBean("genTableColumnService",
			GenTableColumnService.class);
	
	protected static TargetTableFacade targetTableFacade = SpringContextUtils.getBean("targetTableFacade",
			TargetTableFacade.class);

	public Object build(Integer tableId) {
		GenTableQuery queryGenTable = new GenTableQuery();
		queryGenTable.setId(String.valueOf(tableId));
		GenTable genTable = genTableService.get(queryGenTable).getData();
		if (genTable != null) {
			GenTableColumnQuery queryTableColumn = new GenTableColumnQuery();
			queryTableColumn.setGenTable(genTable);
			Response<List<GenTableColumnResult>> tableColumnRes = genTableColumnService
					.findList(new GenTableColumnPO(queryTableColumn));
			List<GenTableColumnResult> tableColumnList = tableColumnRes.getData();
			if (CollectionUtils.isNotEmpty(tableColumnList)) {
				genTable.setGenTableColumns(tableColumnList);
			}
		}
		return genTable;
	}
	
	public abstract String getData();

}
