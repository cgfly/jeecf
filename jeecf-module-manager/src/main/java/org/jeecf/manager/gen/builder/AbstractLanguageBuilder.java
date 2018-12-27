package org.jeecf.manager.gen.builder;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.engine.model.query.WhereEntity;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.facade.TargetTableFacade;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.service.GenTableColumnService;
import org.jeecf.manager.module.template.service.GenTableService;
/**
 * 语言构建类
 * @author jianyiming
 *
 */
public abstract class AbstractLanguageBuilder {
	
	protected static GenTableService genTableService = SpringContextUtils.getBean("genTableService",
			GenTableService.class);
	
	protected static GenTableFacade genTableFacade = SpringContextUtils.getBean("genTableFacade",
			GenTableFacade.class);

	protected static GenTableColumnService genTableColumnService = SpringContextUtils.getBean("genTableColumnService",
			GenTableColumnService.class);
	
	protected static TargetTableFacade targetTableFacade = SpringContextUtils.getBean("targetTableFacade",
			TargetTableFacade.class);

	public Object build(String tableName) {
		GenTableQuery queryGenTable = new GenTableQuery();
		queryGenTable.setName(tableName);
		List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryGenTable)).getData();
		GenTableResult genTable = null;
		if (CollectionUtils.isNotEmpty(genTableList)) {
		    genTable = genTableList.get(0);
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

	/**
	 * 获取表数据 
	 * @param whereEntitys
	 * @return
	 */
	public abstract String getData(List<WhereEntity> whereEntitys);

}
