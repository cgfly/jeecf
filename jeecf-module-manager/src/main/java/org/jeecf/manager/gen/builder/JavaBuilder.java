package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.engine.model.SelectTable;
import org.jeecf.manager.engine.model.SelectTableColumn;
import org.jeecf.manager.gen.language.java.model.JavaCommonTable;
import org.jeecf.manager.gen.language.java.model.JavaTable;
import org.jeecf.manager.gen.language.java.model.JavaTableColumn;
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
	
	private JavaTable javaTable = null;

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
		this.javaTable = genTableJ;
		return genTableJ;
	}
	
	public String getData() {
		if(this.javaTable != null) {
			SelectTable selectTable = new SelectTable();
			List<SelectTableColumn> columnList = new ArrayList<>();
			List<JavaTableColumn> tableColumnList = this.javaTable.getGenTableColumns();
			selectTable.setName(this.javaTable.getClassName());
			selectTable.setTableName(this.javaTable.getName());
			tableColumnList.forEach(tableColumn->{
				SelectTableColumn column = new SelectTableColumn();
				column.setName(tableColumn.getField());
				column.setColumnName(tableColumn.getName());
				columnList.add(column);
			});
			selectTable.setColumnList(columnList);
			return targetTableFacade.selectTable(selectTable).getData();
		}
		return null;
	}

}
