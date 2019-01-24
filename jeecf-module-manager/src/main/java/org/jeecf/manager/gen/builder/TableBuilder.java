package org.jeecf.manager.gen.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.common.gen.model.BaseTable;
import org.jeecf.common.gen.model.BaseTableColumn;
import org.jeecf.common.gen.model.CommonTable;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.model.query.SelectTableColumn;
import org.jeecf.manager.engine.model.query.WhereEntity;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.service.GenTableColumnService;
import org.jeecf.manager.module.template.service.GenTableService;
import org.jeecf.manager.proxy.TargetTableProxy;
import org.springframework.beans.BeanUtils;

/**
 * 表构建类
 * 
 * @author jianyiming
 *
 */
public class TableBuilder {

    private static GenTableService genTableService = SpringContextUtils.getBean("genTableService", GenTableService.class);

    private static GenTableFacade genTableFacade = SpringContextUtils.getBean("genTableFacade", GenTableFacade.class);

    private static GenTableColumnService genTableColumnService = SpringContextUtils.getBean("genTableColumnService", GenTableColumnService.class);

    private static TargetTableProxy targetTableProxy = SpringContextUtils.getBean("targetTableProxy", TargetTableProxy.class);

    private BaseTable baseTable;

    public BaseTable build(String tableName) {
        GenTableQuery queryGenTable = new GenTableQuery();
        queryGenTable.setName(tableName);
        List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryGenTable)).getData();
        GenTableResult genTable = null;
        if (CollectionUtils.isNotEmpty(genTableList)) {
            genTable = genTableList.get(0);
            GenTableColumnQuery queryTableColumn = new GenTableColumnQuery();
            queryTableColumn.setGenTable(genTable);
            Response<List<GenTableColumnResult>> tableColumnRes = genTableColumnService.findList(new GenTableColumnPO(queryTableColumn));
            List<GenTableColumnResult> tableColumnList = tableColumnRes.getData();
            if (CollectionUtils.isNotEmpty(tableColumnList)) {
                genTable.setGenTableColumns(tableColumnList);
            }
        }
        BaseTable baseTable = new BaseTable();
        BeanUtils.copyProperties(genTable, baseTable);
        List<GenTableColumnResult> genTableColumnResultList = genTable.getGenTableColumns();
        baseTable.setGenTableColumns(buildCommonTableColumn(genTableColumnResultList));

        baseTable.setParent(getParent(genTable.getParentTableId()));
        baseTable.setChildList(getChilds(genTable.getId()));
        this.baseTable = baseTable;
        return baseTable;
    }

    /**
     * 获取表数据
     * 
     * @param whereEntitys
     * @return
     */
    public String getData(List<WhereEntity> whereEntitys) {
        if (this.baseTable != null) {
            SelectTable selectTable = SelectTable.Builder.build(this.baseTable.getClassName(), this.baseTable.getName());
            List<SelectTableColumn> columnList = new ArrayList<>();
            List<BaseTableColumn> tableColumnList = this.baseTable.getGenTableColumns();
            tableColumnList.forEach(tableColumn -> {
                columnList.add(SelectTableColumn.Builder.build(tableColumn.getField(), tableColumn.getName()));
            });
            selectTable.setWhereEntitys(whereEntitys);
            selectTable.setSelectTableColumns(columnList);
            return targetTableProxy.selectTable(selectTable).getData();
        }
        return null;
    }

    /**
     * 获取子表数据
     * 
     * @param id
     * @return
     */
    private List<CommonTable> getChilds(String id) {
        List<GenTableResult> tableResultList = TableBuilder.genTableFacade.findChildTables(id).getData();
        List<CommonTable> childTables = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tableResultList)) {
            tableResultList.forEach(tableResult -> {
                CommonTable childTable = new CommonTable();
                BeanUtils.copyProperties(tableResult, childTable);
                List<GenTableColumnResult> genTableColumnResultList = tableResult.getGenTableColumns();
                childTable.setGenTableColumns(buildCommonTableColumn(genTableColumnResultList));
                childTables.add(childTable);
            });
        }
        return childTables;
    }

    /**
     * 获取父表数据
     * 
     * @param id
     * @return
     */
    private CommonTable getParent(String id) {
        GenTable parentTable = TableBuilder.genTableFacade.findParentTable(id).getData();
        CommonTable parentCommonTable = new CommonTable();
        if (parentTable != null) {
            BeanUtils.copyProperties(parentTable, parentCommonTable);
            List<GenTableColumnResult> genTableColumnResultList = parentTable.getGenTableColumns();
            parentCommonTable.setGenTableColumns(buildCommonTableColumn(genTableColumnResultList));
        }
        return parentCommonTable;
    }

    /**
     * 构建 commonTableColumn
     * 
     * @param genTableColumnResultList
     * @return
     */
    private List<BaseTableColumn> buildCommonTableColumn(List<GenTableColumnResult> genTableColumnResultList) {
        List<BaseTableColumn> baseTableColumns = new ArrayList<>();
        genTableColumnResultList.forEach(tableColumnResult -> {
            BaseTableColumn baseTableColumn = new BaseTableColumn();
            BeanUtils.copyProperties(tableColumnResult, baseTableColumn);
            baseTableColumns.add(baseTableColumn);
        });
        return baseTableColumns;
    }

}
