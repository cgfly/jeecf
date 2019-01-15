package org.jeecf.manager.module.template.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.module.template.facade.GenFieldFacade;
import org.jeecf.manager.module.template.model.domain.GenField;
import org.jeecf.manager.module.template.model.po.GenFieldColumnPO;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.result.GenFieldColumnResult;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.jeecf.manager.module.template.model.schema.GenFieldSchema;
import org.jeecf.manager.module.template.service.GenFieldColumnService;
import org.jeecf.manager.module.template.service.GenFieldService;
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
 * 模版参数
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value= {"template/genField"})
@Api(value="genField api",tags={"模版参数接口"})
public class GenFieldController extends BaseController<GenFieldQuery,GenFieldResult,GenFieldSchema,GenField> {

	@Autowired
	private GenFieldService genFieldService;
	
	@Autowired
	private GenFieldColumnService genFieldColumnService;
	
	@Autowired
	private GenFieldFacade genFieldFacade;

	@GetMapping(value= {"","index"})
	@RequiresPermissions("template:genField:view")
	@ApiOperation(value = "视图", notes = "查看模版参数视图")
	@Override
	public String index(ModelMap map) {
		return "module/template/genField";
	}
	
	@PostMapping(value= {"list"})
	@ResponseBody
	@RequiresPermissions("template:genField:view")
	@ApiOperation(value = "列表", notes = "查询模版参数列表")
	@Override
	public Response<List<GenFieldResult>> list(@RequestBody Request<GenFieldQuery,GenFieldSchema> request) {
		Response<List<GenFieldResult>> response = genFieldService.findPageByAuth(new GenFieldPO(request));
		if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
			genFieldService.buildCreateBy(response.getData());
		}
		return  response;
	}
	
	@PostMapping(value= {"column/{genFieldId}"})
	@ResponseBody
	@RequiresPermissions("template:genField:view")
	@ApiOperation(value = "详情列表", notes = "查询模版参数详情列表")
	public Response<List<GenFieldColumnResult>> column(@PathVariable("genFieldId") Integer genFieldId) {
		GenFieldColumnQuery genFieldColumn = new GenFieldColumnQuery();
		genFieldColumn.setGenFieldId(genFieldId);
		return genFieldColumnService.findList(new GenFieldColumnPO(genFieldColumn));
	}
	
	@PostMapping(value= {"save"})
	@ResponseBody
	@RequiresPermissions("template:genField:edit")
	@ApiOperation(value = "更新", notes = "更新模版参数数据")
	@Override
	public Response<GenFieldResult> save(@RequestBody @Validated({Add.class}) GenField genField) {
		if(genField.isNewRecord()) {
			GenFieldQuery query = new GenFieldQuery();
			query.setName(genField.getName());
			query.setSysNamespaceId(NamespaceUtils.getNamespaceId());
			List<GenFieldResult> genFieldList = genFieldService.findList(new GenFieldPO(query)).getData();
			if(CollectionUtils.isNotEmpty(genFieldList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		return genFieldFacade.save(genField);
	}
	
	@PostMapping(value= {"delete/{id}"})
	@ResponseBody
	@RequiresPermissions("template:genField:edit")
	@ApiOperation(value = "删除", notes = "删除模版参数数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		return genFieldFacade.delete(new GenField(id));
	}

}