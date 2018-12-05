package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.gen.language.java.model.JavaCommonTable;
import org.jeecf.manager.gen.language.java.model.JavaTable;
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
	public JavaTable build(Integer tableId) {
		GenTable genTable = (GenTable) super.build(tableId);
		JavaTable genTableJ = new JavaTable();
		BeanUtils.copyProperties(genTable, genTableJ);
		genTableJ.setGenTableColumns(HelperUtils.toColumn(genTable.getGenTableColumns()));
		GenTable parentTable = LanguageBuilder.genTableFacade.findParentTable(genTable.getParentTableId()).getData();
		JavaCommonTable parentCommonTable = new JavaCommonTable();
		if(parentTable != null) {
			BeanUtils.copyProperties(parentTable, parentCommonTable);
			parentCommonTable.setGenTableColumns(HelperUtils.toColumn(parentTable.getGenTableColumns()));
		}
		List<GenTableResult> tableResultList = LanguageBuilder.genTableFacade.findChildTables(genTable.getId()).getData();
		List<JavaCommonTable> childTables = new ArrayList<JavaCommonTable>();
		if(CollectionUtils.isNotEmpty(tableResultList)) {
			tableResultList.forEach(tableResult -> {
				JavaCommonTable childTable = new JavaCommonTable();
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
