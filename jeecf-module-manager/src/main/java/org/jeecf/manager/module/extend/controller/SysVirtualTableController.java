package org.jeecf.manager.module.extend.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
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
 * 虚表 controller
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value= {"extend/sysVirtualTable"})
@Api(value="SysVirtualTable api",tags={"虚表参数接口"})
public class SysVirtualTableController  extends BaseController<SysVirtualTableQuery,SysVirtualTableResult,SysVirtualTableSchema,SysVirtualTable>{
	
	@Autowired
	private SysVirtualTableService sysVirtualTableService;
	
	@Autowired
	private SysVirtualTableColumnService sysVirtualTableColumnService;
	
	@Autowired
	private SysVirtualTableFacade sysVirtualTableFacade;

	@GetMapping(value= {"","index"})
	@RequiresPermissions("extend:sysVirtualTable:view")
	@ApiOperation(value = "视图", notes = "查看虚表参数视图")
	@Override
	protected String index(ModelMap map) {
		return "module/extend/sysVirtualTable";
	}
	
	@PostMapping(value= {"list"})
	@ResponseBody
	@RequiresPermissions("extend:sysVirtualTable:view")
	@ApiOperation(value = "列表", notes = "查询虚表数据")
	@Override
	public Response<List<SysVirtualTableResult>> list(@RequestBody Request<SysVirtualTableQuery, SysVirtualTableSchema> request) {
		return sysVirtualTableService.findPageByAuth(new SysVirtualTablePO(request));
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("extend:sysVirtualTable:edit")
	@ApiOperation(value = "更新", notes = "更新虚表数据")
	@Override
	public Response<SysVirtualTableResult> save(@RequestBody @Validated({Add.class}) SysVirtualTable sysVirtualTable) {
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

	@PostMapping(value= {"column/{sysVirtualTableId}"})
	@ResponseBody
	@RequiresPermissions("extend:sysVirtualTable:view")
	@ApiOperation(value = "详情列表", notes = "查询模版参数详情列表")
	public Response<List<SysVirtualTableColumnResult>> column(@PathVariable("sysVirtualTableId") Integer sysVirtualTableId) {
		SysVirtualTableColumnQuery query = new SysVirtualTableColumnQuery();
		query.setSysVirtualTableId(sysVirtualTableId);
		return sysVirtualTableColumnService.findList(new SysVirtualTableColumnPO(query));
	}

}
