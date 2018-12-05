package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.gen.language.go.model.GoCommonTable;
import org.jeecf.manager.gen.language.go.model.GoTable;
import org.jeecf.manager.gen.language.go.utils.HelperUtils;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.springframework.beans.BeanUtils;
/**
 * Go构建者
 * @author jianyiming
 *
 */
public class GoBuilder  extends LanguageBuilder{
	
	@Override
	public GoTable build(Integer tableId) {
		GenTable genTable = (GenTable) super.build(tableId);
		GoTable genTableG = new GoTable();
		BeanUtils.copyProperties(genTable, genTableG);
		genTableG.setGenTableColumns(HelperUtils.toColumn(genTable.getGenTableColumns()));
		GenTable parentTable = LanguageBuilder.genTableFacade.findParentTable(genTable.getParentTableId()).getData();
		GoCommonTable parentCommonTable = new GoCommonTable();
		if(parentTable != null) {
			BeanUtils.copyProperties(parentTable, parentCommonTable);
			parentCommonTable.setGenTableColumns(HelperUtils.toColumn(parentTable.getGenTableColumns()));
		}
		List<GenTableResult> tableResultList = LanguageBuilder.genTableFacade.findChildTables(genTable.getId()).getData();
		List<GoCommonTable> childTables = new ArrayList<GoCommonTable>();
		if(CollectionUtils.isNotEmpty(tableResultList)) {
			tableResultList.forEach(tableResult -> {
				GoCommonTable childTable = new GoCommonTable();
				BeanUtils.copyProperties(tableResult, childTable);
				childTable.setGenTableColumns(HelperUtils.toColumn(tableResult.getGenTableColumns()));
				childTables.add(childTable);
			});
		}
		genTableG.setParent(parentCommonTable);
		genTableG.setChildList(childTables);
		return genTableG;
	}

}
