package org.jeecf.manager.module.gen.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.AbstractController;
import org.jeecf.manager.module.gen.model.domian.SysTreeDict;
import org.jeecf.manager.module.gen.model.po.SysTreeDictPO;
import org.jeecf.manager.module.gen.model.query.SysTreeDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTreeDictResult;
import org.jeecf.manager.module.gen.service.SysTreeDictService;
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

@Controller
@RequestMapping(value = { "gen/sysTreeDict" })
@Api(value = "sysTreeDict api", tags = { "树组接口" })
public class SysTreeDictController extends AbstractController {

	@Autowired
	private SysTreeDictService sysTreeDictService;

	@GetMapping(value = { "", "index" })
	@RequiresPermissions("gen:sysTreeDict:view")
	@ApiOperation(value = "视图", notes = "查看系统权限视图")
	@Override
	public String index(ModelMap map) {
		return "module/gen/sysTreeDict";
	}

	@PostMapping(value = { "list" })
	@ResponseBody
	@RequiresPermissions("gen:sysTreeDict:view")
	@ApiOperation(value = "列表", notes = "查询系统权限列表")
	public Response<List<SysTreeDictResult>> list(@RequestBody SysTreeDictQuery sysTreeDictQuery) {
		Response<List<SysTreeDictResult>> sysTreeDictRes = sysTreeDictService
				.getTreeData(new SysTreeDictPO(sysTreeDictQuery));
		if (CollectionUtils.isNotEmpty(sysTreeDictRes.getData())) {
			sysTreeDictRes.getData().forEach(sysTreeDictResult -> {
				sysTreeDictResult.toCovert();
			});
		}
		return sysTreeDictRes;
	}

	@PostMapping(value = { "getTreeData" })
	@ResponseBody
	@RequiresPermissions("gen:sysTreeDict:view")
	@ApiOperation(value = "列表", notes = "查询系统权限数表格列表")
	public Response<List<SysTreeDictResult>> getTreeData(SysTreeDictQuery sysTreeDictQuery) {
		return sysTreeDictService.getTreeData(new SysTreeDictPO(sysTreeDictQuery));
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("gen:sysTreeDict:edit")
	@ApiOperation(value = "更新", notes = "更新系统权限数据")
	public Response<SysTreeDictResult> save(@RequestBody @Validated({ Add.class }) SysTreeDict sysTreeDict) {
		return sysTreeDictService.save(sysTreeDict);
	}

	@PostMapping(value = { "delete/{id}" })
	@ResponseBody
	@RequiresPermissions("gen:sysTreeDict:edit")
	@ApiOperation(value = "删除", notes = "删除系统权限数据")
	public Response<Integer> delete(@PathVariable("id") String id) {
		return sysTreeDictService.delete(new SysTreeDict(id));
	}

}
