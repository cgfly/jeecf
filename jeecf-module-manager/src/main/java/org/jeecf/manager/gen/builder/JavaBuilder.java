package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.gen.language.java.model.CommonTable;
import org.jeecf.manager.gen.language.java.model.GenTableJ;
import org.jeecf.manager.gen.language.java.utils.HelperUtils;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.springframework.beans.BeanUtils;

/**
 * java 构建者
 * 
 * @author jianyiming
 *
 */
public class JavaBuilder extends LanguageBuilder {

	@Override
	public Object build(Integer tableId) {
		GenTable genTable = (GenTable) super.build(tableId);
		GenTableJ genTableJ = new GenTableJ();
		BeanUtils.copyProperties(genTable, genTableJ);
		genTableJ.setGenTableColumns(HelperUtils.toColumn(genTable.getGenTableColumns()));
		GenTable parentTable = LanguageBuilder.genTableFacade.findParentTable(genTable.getParentTableId()).getData();
		CommonTable parentCommonTable = new CommonTable();
		if(parentTable != null) {
			BeanUtils.copyProperties(parentTable, parentCommonTable);
			parentCommonTable.setGenTableColumns(HelperUtils.toColumn(parentTable.getGenTableColumns()));
		}
		List<GenTableResult> tableResultList = LanguageBuilder.genTableFacade.findChildTables(genTable.getId()).getData();
		List<CommonTable> childTables = new ArrayList<CommonTable>();
		if(CollectionUtils.isNotEmpty(tableResultList)) {
			tableResultList.forEach(tableResult -> {
				CommonTable childTable = new CommonTable();
				BeanUtils.copyProperties(tableResult, childTable);
				childTable.setGenTableColumns(HelperUtils.toColumn(tableResult.getGenTableColumns()));
				childTables.add(childTable);
			});
		}
		genTableJ.setParent(parentCommonTable);
		genTableJ.setChildList(childTables);
		return genTableJ;
	}

}
