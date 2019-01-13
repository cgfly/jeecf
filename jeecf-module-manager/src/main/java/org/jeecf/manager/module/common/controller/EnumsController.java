package org.jeecf.manager.module.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.TreeEventEnum;
import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.osgi.enums.BoundleEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = { "common/enums" })
@Api(value = "common api", tags = { "系统数据源接口" })
public class EnumsController {
	
	@PostMapping(value = { "list" })
	@ResponseBody
	@ApiOperation(value = "列表", notes = "查询枚举列表")
	public Response<Map<String,String>> list() {
		Map<String,String> result = new HashMap<>();
		result.put("treeEventEnum", TreeEventEnum.toJsonString());
		result.put("tableTypeEnum", TableTypeEnum.toJsonString());
		result.put("osgiBoundleTypeEnum", BoundleEnum.toJsonString());
		return new Response<>(result);
	}

}
