package org.jeecf.manager.module.config.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.validator.constraints.NotEmpty;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.BaseController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;
import org.jeecf.manager.module.config.model.schema.SysDbsourceSchema;
import org.jeecf.manager.module.config.service.SysDbsourceService;
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
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "config/sysDbsource" })
@Api(value = "{className} api", tags = { "系统数据源接口" })
public class SysDbsourceController
		extends BaseController<SysDbsourceQuery, SysDbsourceResult, SysDbsourceSchema, SysDbsource> {

	@Autowired
	private SysDbsourceService sysDbsourceService;

	@GetMapping(value = { "", "index" })
	@RequiresPermissions("config:sysDbsource:view")
	@ApiOperation(value = "视图", notes = "查看系统数据源视图")
	@Override
	public String index(ModelMap map) {
		return "module/config/sysDbsource";
	}

	@PostMapping(value = { "list" })
	@ResponseBody
	@RequiresPermissions("config:sysDbsource:view")
	@ApiOperation(value = "列表", notes = "查询系统数据源列表")
	@Override
	public Response<List<SysDbsourceResult>> list(@RequestBody Request<SysDbsourceQuery, SysDbsourceSchema> request) {
		SysDbsourceSchema schema = request.getSchema();
		if (schema == null) {
			schema = new SysDbsourceSchema();
			request.setSchema(schema);
		}
		schema.setPassword(false);
		Response<List<SysDbsourceResult>> sysDbsourceRes = sysDbsourceService
				.findPageByAuth(new SysDbsourcePO(request));
		List<SysDbsourceResult> sysDbsourceList = sysDbsourceRes.getData();
		sysDbsourceList.forEach(sysDbsourceEnum -> {
			sysDbsourceEnum.setUsableName(EnumUtils.Usable.getName(sysDbsourceEnum.getUsable()));
		});
		return sysDbsourceRes;
	}

	@PostMapping(value = { "save" })
	@ResponseBody
	@RequiresPermissions("config:sysDbsource:edit")
	@ApiOperation(value = "更新", notes = "更新系统数据源数据")
	@Override
	public Response<Integer> save(@RequestBody @Validated({ Add.class }) SysDbsource sysDbsource) {
		if (sysDbsource.isNewRecord()) {
			SysDbsourceQuery query = new SysDbsourceQuery();
			query.setKeyName(sysDbsource.getKeyName());
			List<SysDbsourceResult> sysDbsourcceList = sysDbsourceService.findList(new SysDbsourcePO(query)).getData();
			if (CollectionUtils.isNotEmpty(sysDbsourcceList)) {
				throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
			}
		}
		Response<Integer> res = sysDbsourceService.saveByAuth(sysDbsource);
		if (res.isSuccess() && res.getData() > 0) {
			SysDbsource sysDb = sysDbsourceService.get(sysDbsource).getData();
			if (sysDb != null) {
				boolean flag = JdbcUtils.test(sysDb.getUrl(), sysDb.getUserName(),
						sysDb.getPassword());
				if (flag) {
					sysDb.setUsable(EnumUtils.Usable.YES.getCode());
				} else {
					sysDb.setUsable(EnumUtils.Usable.NO.getCode());
				}
				sysDbsourceService.saveByAuth(sysDb);
			}
		}
		return res;
	}

	@PostMapping(value = { "delete/{id}" })
	@ResponseBody
	@RequiresPermissions("config:sysDbsource:edit")
	@ApiOperation(value = "删除", notes = "删除系统数据源数据")
	@Override
	public Response<Integer> delete(@PathVariable("id") String id) {
		SysDbsource sysDbsource = sysDbsourceService.get(new SysDbsource(id)).getData();
		if (sysDbsource != null) {
			String keyName = sysDbsource.getKeyName();
			String defaultKeyName = DynamicDataSourceContextHolder.getDafualtKey();
			if (!defaultKeyName.equals(keyName)) {
				String currentKeyName = DynamicDataSourceContextHolder.getCurrentDataSourceValue();
				if (!currentKeyName.equals(keyName)) {
					return sysDbsourceService.deleteByAuth(sysDbsource);
				}
				throw new BusinessException(BusinessErrorEnum.DARASOURCE_KEY_IS_CURRENT);
			}
			throw new BusinessException(BusinessErrorEnum.DARASOURCE_KEY_IS_DEFAULT);
		}
		throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
	}

	@PostMapping(value = { "effect/{keyName}" })
	@ResponseBody
	@RequiresPermissions("config:sysDbsource:view")
	@ApiOperation(value = "生效", notes = "生效选中系统数据源")
	public Response<Integer> effect(@NotEmpty @PathVariable("keyName") String keyName) {
		SysDbsourceQuery sysDbsource = new SysDbsourceQuery();
		sysDbsource.setKeyName(keyName);
		List<SysDbsourceResult> sysDbSourceList = sysDbsourceService.findListByAuth(new SysDbsourcePO(sysDbsource))
				.getData();
		if (CollectionUtils.isNotEmpty(sysDbSourceList)) {
			for (SysDbsource sysDbSource : sysDbSourceList) {
				if (keyName.equals(sysDbSource.getKeyName())) {
					if (sysDbSource.getUsable() == EnumUtils.Usable.NO.getCode()) {
						throw new BusinessException(BusinessErrorEnum.DB_CONNECT_EXCEPTION);
					}
					DynamicDataSourceContextHolder.setCurrentDataSourceValue(keyName);
					return new Response<Integer>(1);
				}
			}
		}
		throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
	}

}