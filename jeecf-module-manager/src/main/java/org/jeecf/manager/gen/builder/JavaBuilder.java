package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.model.query.SelectTableColumn;
import org.jeecf.manager.engine.model.query.WhereEntity;
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
public class JavaBuilder extends AbstractLanguageBuilder {
	
	private JavaTable javaTable = null;

	@Override
	public JavaTable build(String tableName) {
		GenTable genTable = (GenTable) super.build(tableName);
		JavaTable genTableJ = new JavaTable();
		BeanUtils.copyProperties(genTable, genTableJ);
		genTableJ.setGenTableColumns(HelperUtils.toColumn(genTable.getGenTableColumns()));
		GenTable parentTable = AbstractLanguageBuilder.genTableFacade.findParentTable(genTable.getParentTableId()).getData();
		JavaCommonTable parentCommonTable = new JavaCommonTable();
		if(parentTable != null) {
			BeanUtils.copyProperties(parentTable, parentCommonTable);
			parentCommonTable.setGenTableColumns(HelperUtils.toColumn(parentTable.getGenTableColumns()));
		}
		List<GenTableResult> tableResultList = AbstractLanguageBuilder.genTableFacade.findChildTables(genTable.getId()).getData();
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
	
	@Override
	public String getData(List<WhereEntity> whereEntitys) {
		if(this.javaTable != null) {
			SelectTable selectTable = SelectTable.Builder.build(this.javaTable.getClassName(), this.javaTable.getName());
			List<SelectTableColumn> columnList = new ArrayList<>();
			List<JavaTableColumn> tableColumnList = this.javaTable.getGenTableColumns();
			tableColumnList.forEach(tableColumn->{
				columnList.add(SelectTableColumn.Builder.build(tableColumn.getField(), tableColumn.getName()));
			});
			selectTable.setWhereEntitys(whereEntitys);
			selectTable.setSelectTableColumns(columnList);
			return targetTableProxy.selectTable(selectTable).getData();
		}
		return null;
	}

}
