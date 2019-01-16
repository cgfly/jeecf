package org.jeecf.manager.module.gen.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.DbsourceUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.module.gen.model.domian.SysTableDict;
import org.jeecf.manager.module.gen.model.po.SysTableDictPO;
import org.jeecf.manager.module.gen.model.query.SysTableDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTableDictResult;
import org.jeecf.manager.module.gen.model.schema.SysTableDictSchema;
import org.jeecf.manager.module.gen.service.SysTableDictService;
import org.jeecf.manager.module.template.model.po.GenTablePO;
import org.jeecf.manager.module.template.model.query.GenTableQuery;
import org.jeecf.manager.module.template.model.result.GenTableResult;
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
 * 表组字典 controller
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "gen/sysTableDict" })
@Api(value = "sysTableDict api", tags = { "表组字典接口" })
@Validated
public class SysTableDictController  implements CurdController<SysTableDictQuery,SysTableDictResult,SysTableDictSchema,SysTableDict> {

	@Autowired
	private SysTableDictService sysTableDictService;
	
	@Autowired
	private GenTableService genTableService;

	@GetMapping(value = { "", "index" })
	@RequiresPermissions("gen:sysTableDict:view")
	@ApiOperation(value = "视图", notes = "查看系统字典视图")
	@Override
	public String index(ModelMap map) {
		return "module/gen/sysTableDict";
	}

	@PostMapping(value = { "list" })
	@ResponseBody
	@RequiresPermissions("gen:sysTableDict:view")
	@ApiOperation(value = "列表", notes = "查询系统字典列表")
	@Override
	public Response<List<SysTableDictResult>> list(@RequestBody Request<SysTableDictQuery, SysTableDictSchema> request) {
		Response<List<SysTableDictResult>> response =  sysTableDictService.findPageByAuth(new SysTableDictPO(request));
		if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
			sysTableDictService.buildCreateBy(response.getData());
		}
		return response;
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("gen:sysTableDict:edit")
	@ApiOperation(value = "更新", notes = "更新系统字典数据")
	@Override
	public Response<SysTableDictResult> save(@RequestBody @Validated({Add.class}) SysTableDict sysTableDict) {
		if(sysTableDict.isNewRecord()) {
			SysTableDictQuery query = new SysTableDictQuery();
			query.setName(sysTableDict.getName());
			query.setTableName(sysTableDict.getTableName());
			query.setSysDbsourceId(DbsourceUtils.getSysDbsourceId());
			query.setSysNamespaceId(NamespaceUtils.getNamespaceId());
			List<SysTableDictResult> sysTableDictList = sysTableDictService.findList(new SysTableDictPO(query)).getData();
			if(CollectionUtils.isNotEmpty(sysTableDictList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		return sysTableDictService.saveByAuth(sysTableDict);
	}

	@PostMapping(value = { "delete/{id}" })
	@ResponseBody
	@RequiresPermissions("gen:sysTableDict:edit")
	@ApiOperation(value = "删除", notes = "删除系统字典数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		return sysTableDictService.deleteByAuth(new SysTableDict(id));
	}
	
	@PostMapping(value = { "tables" })
	@ResponseBody
	@RequiresPermissions("gen:sysTableDict:view")
	@ApiOperation(value = "列表", notes = "查询系统字典列表")
	public Response<List<GenTableResult>> tables() {
		return genTableService.findListByAuth(new GenTablePO(new GenTableQuery()));
	}


}
