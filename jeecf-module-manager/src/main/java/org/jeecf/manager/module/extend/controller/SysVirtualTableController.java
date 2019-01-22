package org.jeecf.manager.module.extend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.enums.IfTypeEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.DbsourceUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.engine.model.create.CreateTable;
import org.jeecf.manager.engine.model.create.CreateTableColumn;
import org.jeecf.manager.engine.model.create.CreateTableColumn.Builder;
import org.jeecf.manager.engine.model.schema.SchemaTable;
import org.jeecf.manager.engine.utils.SqlHelper;
import org.jeecf.manager.module.extend.facade.SysVirtualTableFacade;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTable;
import org.jeecf.manager.module.extend.model.po.SysVirtualTableColumnPO;
import org.jeecf.manager.module.extend.model.po.SysVirtualTablePO;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableColumnQuery;
import org.jeecf.manager.module.extend.model.query.SysVirtualTableQuery;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableColumnResult;
import org.jeecf.manager.module.extend.model.result.SysVirtualTableResult;
import org.jeecf.manager.module.extend.model.schema.SysVirtualTableSchema;
import org.jeecf.manager.module.extend.service.SysVirtualTableColumnService;
import org.jeecf.manager.module.extend.service.SysVirtualTableService;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.service.GenTableService;
import org.jeecf.manager.proxy.TargetTableProxy;
import org.jeecf.manager.validate.groups.Add;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 虚表 controller
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "extend/sysVirtualTable" })
@Api(value = "SysVirtualTable api", tags = { "虚表参数接口" })
public class SysVirtualTableController implements CurdController<SysVirtualTableQuery, SysVirtualTableResult, SysVirtualTableSchema, SysVirtualTable> {

    @Autowired
    private SysVirtualTableService sysVirtualTableService;

    @Autowired
    private SysVirtualTableColumnService sysVirtualTableColumnService;

    @Autowired
    private SysVirtualTableFacade sysVirtualTableFacade;

    @Autowired
    private TargetTableProxy targetTableProxy;

    @Autowired
    private GenTableFacade genTableFacade;

    @Autowired
    private GenTableService genTableService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("extend:sysVirtualTable:view")
    @ApiOperation(value = "视图", notes = "查看虚表参数视图")
    @Override
    public String index(ModelMap map) {
        return "module/extend/sysVirtualTable";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:view")
    @ApiOperation(value = "列表", notes = "查询虚表数据")
    @Override
    public Response<List<SysVirtualTableResult>> list(@RequestBody Request<SysVirtualTableQuery, SysVirtualTableSchema> request) {
        Response<List<SysVirtualTableResult>> response = sysVirtualTableService.findPageByAuth(new SysVirtualTablePO(request));
        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
            sysVirtualTableService.buildCreateBy(response.getData());
        }
        return response;
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:edit")
    @ApiOperation(value = "更新", notes = "更新虚表数据")
    @Override
    public Response<SysVirtualTableResult> save(@RequestBody @Validated({ Add.class }) SysVirtualTable sysVirtualTable) {
        if (sysVirtualTable.isNewRecord()) {
            SysVirtualTableQuery query = new SysVirtualTableQuery();
            query.setName(sysVirtualTable.getName());
            query.setSysNamespaceId(NamespaceUtils.getNamespaceId());
            query.setSysDbsourceId(DbsourceUtils.getSysDbsourceId());
            List<SysVirtualTableResult> sysVirtualTableResultList = sysVirtualTableService.findList(new SysVirtualTablePO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysVirtualTableResultList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
        }
        return sysVirtualTableFacade.save(sysVirtualTable);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:edit")
    @ApiOperation(value = "删除", notes = "删除虚表数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysVirtualTableService.delete(new SysVirtualTable(id));
    }

    @PostMapping(value = { "column/{sysVirtualTableId}" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:view")
    @ApiOperation(value = "详情列表", notes = "查询模版参数详情列表")
    public Response<List<SysVirtualTableColumnResult>> column(@PathVariable("sysVirtualTableId") Integer sysVirtualTableId) {
        SysVirtualTableColumnQuery query = new SysVirtualTableColumnQuery();
        query.setSysVirtualTableId(sysVirtualTableId);
        Response<List<SysVirtualTableColumnResult>> sysVirtualTableColumnRes = sysVirtualTableColumnService.findList(new SysVirtualTableColumnPO(query));
        if (CollectionUtils.isNotEmpty(sysVirtualTableColumnRes.getData())) {
            sysVirtualTableColumnRes.getData().forEach(sysVirtualTableColumnResult -> {
                sysVirtualTableColumnResult.toCovert();
            });
        }
        return sysVirtualTableColumnRes;
    }

    @PostMapping(value = { "syncGen/{id}" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:edit")
    @ApiOperation(value = "同步", notes = "同步虚表数据")
    public Response<Integer> syncGen(@PathVariable("id") String id) {
        Response<SysVirtualTableResult> tableRes = sysVirtualTableService.get(new SysVirtualTable(id));
        if (tableRes.getData() != null) {
            SysVirtualTableColumnQuery query = new SysVirtualTableColumnQuery();
            query.setSysVirtualTableId(Integer.valueOf(tableRes.getData().getId()));
            Response<List<SysVirtualTableColumnResult>> sysVirtualTableColumnRes = sysVirtualTableColumnService.findList(new SysVirtualTableColumnPO(query));

            GenTableQuery queryTable = new GenTableQuery();
            queryTable.setName(tableRes.getData().getName());
            List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryTable)).getData();
            if (CollectionUtils.isNotEmpty(genTableList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }

            GenTable genTable = new GenTable();
            BeanUtils.copyProperties(tableRes.getData(), genTable);
            genTable.setId(null);
            genTable.setClassName(HumpUtils.lineToHump(tableRes.getData().getName()));
            List<GenTableColumnResult> genTableColumns = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(sysVirtualTableColumnRes.getData())) {
                sysVirtualTableColumnRes.getData().forEach(tableColumn -> {
                    GenTableColumnResult genTableColumn = new GenTableColumnResult();
                    BeanUtils.copyProperties(tableColumn, genTableColumn);
                    genTableColumn.setJdbcType(SqlHelper.toJdbcType(tableColumn.getType(), tableColumn.getLength(), tableColumn.getDecimalLength()));
                    genTableColumn.setField(genTableColumn.getName());
                    genTableColumn.setIsNull(tableColumn.getIsNotNull());
                    genTableColumns.add(genTableColumn);
                });
            }
            genTable.setGenTableColumns(genTableColumns);
            genTableFacade.saveTable(genTable);
            return new Response<>(1);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "createTable/{id}" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:edit")
    @ApiOperation(value = "建表", notes = "建表")
    public Response<Integer> createTable(@PathVariable("id") String id) {
        Response<SysVirtualTableResult> tableRes = sysVirtualTableService.getByAuth(new SysVirtualTable(id));
        if (tableRes.getData() != null) {
            SysVirtualTableResult table = tableRes.getData();
            Response<SchemaTable> schemaTableRes = targetTableProxy.getTable(table.getName());
            if (schemaTableRes.getData() != null) {
                throw new BusinessException(BusinessErrorEnum.TARGET_TABLE_EXIST);
            }
            SysVirtualTableColumnQuery query = new SysVirtualTableColumnQuery();
            query.setSysVirtualTableId(Integer.valueOf(tableRes.getData().getId()));
            Response<List<SysVirtualTableColumnResult>> sysVirtualTableColumnRes = sysVirtualTableColumnService.findList(new SysVirtualTableColumnPO(query));
            List<CreateTableColumn> createTableColumns = new ArrayList<>();
            sysVirtualTableColumnRes.getData().forEach(tableColumn -> {
                Builder builder = CreateTableColumn.builder();
                builder.setColumnName(tableColumn.getName()).setType(SqlHelper.toJdbcType(tableColumn.getType(), tableColumn.getLength(), tableColumn.getDecimalLength()))
                        .setComment(tableColumn.getComment());
                if (tableColumn.getIsKey() == IfTypeEnum.YES.getCode()) {
                    boolean isAuto = tableColumn.getIsAuto() == IfTypeEnum.YES.getCode();
                    builder.isPrimaryKey(isAuto);
                }
                createTableColumns.add(builder.build());
            });
            CreateTable createTable = CreateTable.builder().setTableName(table.getName()).setComment(table.getComment()).addCreateTableColumns(createTableColumns).build();
            return targetTableProxy.create(createTable);
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @PostMapping(value = { "dropTable/{id}" })
    @ResponseBody
    @RequiresPermissions("extend:sysVirtualTable:edit")
    @ApiOperation(value = "删表", notes = "删表")
    public Response<Integer> dropTable(@PathVariable("id") String id) {
        Response<SysVirtualTableResult> tableRes = sysVirtualTableService.getByAuth(new SysVirtualTable(id));
        if (tableRes.getData() != null) {
            SysVirtualTableResult table = tableRes.getData();
            Response<SchemaTable> schemaTableRes = targetTableProxy.getTable(table.getName());
            if (schemaTableRes.getData() == null) {
                throw new BusinessException(BusinessErrorEnum.TARGET_TABLE_NOT_EXIST);
            }
            return targetTableProxy.drop(table.getName());
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

}
