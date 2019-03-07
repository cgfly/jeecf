package org.jeecf.manager.module.template.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.template.facade.GenTableFacade;
import org.jeecf.manager.module.template.model.domain.GenTable;
import org.jeecf.manager.module.template.model.po.GenTableColumnPO;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableColumnQuery;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.module.template.model.result.GenTableResult;
import org.jeecf.manager.module.template.model.schema.GenTableSchema;
import org.jeecf.manager.module.template.service.GenTableColumnService;
import org.jeecf.manager.module.template.service.GenTableService;
import org.jeecf.manager.validate.groups.Add;
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
 * 业务表 controller
 * 
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "template/genTable" })
@Api(value = "GenTable api", tags = { "代码生成业务表接口" })
public class GenTableController implements CurdController<GenTableQuery, GenTableResult, GenTableSchema, GenTable> {

    @Autowired
    private GenTableService genTableService;

    @Autowired
    private GenTableColumnService genTableColumnService;

    @Autowired
    private GenTableFacade genTableFacade;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.genTable.view}")
    @ApiOperation(value = "视图", notes = "查看代码生成业务表视图")
    @Override
    public String index(ModelMap map) {
        return "module/template/genTable";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.genTable.view}")
    @ApiOperation(value = "列表", notes = "查询代码生成业务表数据")
    @Override
    public Response<List<GenTableResult>> list(@RequestBody Request<GenTableQuery, GenTableSchema> request) {
        Response<List<GenTableResult>> response = genTableService.findPageByAuth(new GenTablePO(request));
        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
            genTableService.buildCreateBy(response.getData());
        }
        return response;
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.genTable.edit}")
    @ApiOperation(value = "更新", notes = "更新代码生成业务表数据")
    @Override
    public Response<GenTableResult> save(@RequestBody @Validated({ Add.class }) GenTable genTable) {
        if (genTable.isNewRecord()) {
            GenTableQuery queryTable = new GenTableQuery();
            queryTable.setName(genTable.getName());
            List<GenTableResult> genTableList = genTableService.findListByAuth(new GenTablePO(queryTable)).getData();
            if (CollectionUtils.isNotEmpty(genTableList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
        }
        return genTableFacade.saveTable(genTable);
    }

    @PostMapping(value = { "queryBaseTableList" })
    @ResponseBody
    @RequiresPermissions("${permission.genTable.view}")
    @ApiOperation(value = "列表", notes = "查询代码生成基本表数据")
    public Response<List<GenTableResult>> getBaseTableList() {
        return genTableService.findListByAuth(new GenTablePO(new GenTableQuery()));
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.genTable.edit}")
    @ApiOperation(value = "删除", notes = "删除代码生成业务表数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return genTableFacade.deleteTable(new GenTable(id));
    }

    @PostMapping(value = { "queryBaseTableColumnList/{tableName}" })
    @ResponseBody
    @RequiresPermissions("${permission.genTable.view}")
    @ApiOperation(value = "列表", notes = "查询代码生成基本字段表数据")
    public Response<List<GenTableColumnResult>> getBaseTableColumnList(@PathVariable String tableName) {
        GenTableQuery queryTable = new GenTableQuery();
        queryTable.setName(tableName);
        Response<List<GenTableResult>> genTableRes = genTableService.findListByAuth(new GenTablePO(queryTable));
        if (CollectionUtils.isNotEmpty(genTableRes.getData())) {
            GenTableColumnQuery queryTableColumn = new GenTableColumnQuery();
            queryTableColumn.setGenTable(genTableRes.getData().get(0));
            Response<List<GenTableColumnResult>> gebTableColumnRes = genTableColumnService.findList(new GenTableColumnPO(queryTableColumn));
            if (CollectionUtils.isNotEmpty(gebTableColumnRes.getData())) {
                gebTableColumnRes.getData().forEach(tableColumn -> {
                    tableColumn.coverField(tableColumn);
                });
            }
            return gebTableColumnRes;
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

}
