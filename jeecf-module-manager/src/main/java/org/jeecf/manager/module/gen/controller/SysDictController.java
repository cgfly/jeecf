package org.jeecf.manager.module.gen.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.module.gen.model.domian.SysDict;
import org.jeecf.manager.module.gen.model.po.SysDictPO;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.result.SysDictResult;
import org.jeecf.manager.module.gen.model.schema.SysDictSchema;
import org.jeecf.manager.module.gen.service.SysDictService;
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
 * 数据字典
 * 
 * @author GloryJian
 *
 */
@Controller
@RequestMapping(value = { "gen/sysDict" })
@Api(value = "sysDict api", tags = { "系统字典接口" })
@Validated
public class SysDictController implements CurdController<SysDictQuery,SysDictResult,SysDictSchema,SysDict>  {

	@Autowired
	private SysDictService sysDictService;

	@GetMapping(value = { "", "index" })
	@RequiresPermissions("gen:sysDict:view")
	@ApiOperation(value = "视图", notes = "查看系统字典视图")
	@Override
	public String index(ModelMap map) {
		return "module/gen/sysDict";
	}

	@PostMapping(value = { "list" })
	@ResponseBody
	@RequiresPermissions("gen:sysDict:view")
	@ApiOperation(value = "列表", notes = "查询系统字典列表")
	@Override
	public Response<List<SysDictResult>> list(@RequestBody Request<SysDictQuery, SysDictSchema> request) {
		Response<List<SysDictResult>> response =  sysDictService.findPageByAuth(new SysDictPO(request));
		if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
			sysDictService.buildCreateBy(response.getData());
		}
		return response;
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("gen:sysDict:edit")
	@ApiOperation(value = "更新", notes = "更新系统字典数据")
	@Override
	public Response<SysDictResult> save(@RequestBody @Validated({Add.class}) SysDict sysDict) {
		sysDict.setType(StringUtils.lowerCase(sysDict.getType()));
		if(!sysDict.getLabel().contains(SplitCharEnum.UNDERLINE.getName())) {
			sysDict.setLabel(HumpUtils.humpToLine2(sysDict.getLabel()));
		} 
		sysDict.setLabel(StringUtils.upperCase(sysDict.getLabel()));
		if(sysDict.isNewRecord()) {
			SysDictQuery query = new SysDictQuery();
			query.setType(sysDict.getType());
			query.setLabel(sysDict.getLabel());
			query.setSysNamespaceId(NamespaceUtils.getNamespaceId());
			List<SysDictResult> sysDictList = sysDictService.findList(new SysDictPO(query)).getData();
			if(CollectionUtils.isNotEmpty(sysDictList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		return sysDictService.saveByAuth(sysDict);
	}

	@PostMapping(value = { "delete/{id}" })
	@ResponseBody
	@RequiresPermissions("gen:sysDict:edit")
	@ApiOperation(value = "删除", notes = "删除系统字典数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		return sysDictService.deleteByAuth(new SysDict(id));
	
	}


}