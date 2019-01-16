package org.jeecf.manager.module.config.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.config.model.domain.SysOffice;
import org.jeecf.manager.module.config.model.po.SysOfficePO;
import org.jeecf.manager.module.config.model.query.SysOfficeQuery;
import org.jeecf.manager.module.config.model.result.SysOfficeResult;
import org.jeecf.manager.module.config.model.schema.SysOfficeSchema;
import org.jeecf.manager.module.config.service.SysOfficeService;
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
 * 组织结构 controller
 * @author jianyiming
 *
 */
@Controller
@RequestMapping(value = { "config/sysOffice" })
@Api(value = "sysOffice api", tags = { "系统组织结构接口" })
public class SysOfficeController implements CurdController<SysOfficeQuery, SysOfficeResult, SysOfficeSchema, SysOffice> {
	
	@Autowired
	private SysOfficeService sysOfficeService;
	
	@GetMapping(value= {"","index"})
	@RequiresPermissions("config:sysOffice:view")
	@ApiOperation(value = "视图", notes = "查看组织结构视图")
	@Override
	public String index(ModelMap map) {
		return "module/config/sysOffice";
	}
	
	@PostMapping(value= {"list"})
	@ResponseBody
	@RequiresPermissions("config:sysOffice:view")
	@ApiOperation(value = "列表", notes = "查询组织结构列表")
	@Override
	public Response<List<SysOfficeResult>> list(@RequestBody Request<SysOfficeQuery,SysOfficeSchema> sysOfficeQuery) {
		return sysOfficeService.getTreeData(new SysOfficePO(sysOfficeQuery));
	}
	
	@PostMapping(value = { "getTreeData" })
	@ResponseBody
	@RequiresPermissions("config:sysOffice:view")
	@ApiOperation(value = "列表", notes = "查询组织结构表格列表")
	public Response<List<SysOfficeResult>> getTreeData(SysOfficeQuery sysOfficeQuery) {
		return sysOfficeService.getTreeData(new SysOfficePO(sysOfficeQuery));
	}
	
	@PostMapping(value= {"save"})
	@ResponseBody
	@RequiresPermissions("config:sysOffice:edit")
	@ApiOperation(value = "更新", notes = "更新组织结构数据")
	@Override
	public Response<SysOfficeResult> save(@RequestBody @Validated({Add.class}) SysOffice sysOffice) {
		if(sysOffice.isNewRecord()) {
			SysOfficeQuery query = new SysOfficeQuery();
			query.setEnname(sysOffice.getEnname());
			List<SysOfficeResult> sysOfficeList = sysOfficeService.findList(new SysOfficePO(query)).getData();
			if(CollectionUtils.isNotEmpty(sysOfficeList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		return sysOfficeService.save(sysOffice);
	}
	
	@PostMapping(value= {"delete/{id}"})
	@ResponseBody
	@RequiresPermissions("config:sysOffice:edit")
	@ApiOperation(value = "删除", notes = "删除组织结构数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		return sysOfficeService.deleteWithChilds(new SysOffice(id));
	}

	
}
