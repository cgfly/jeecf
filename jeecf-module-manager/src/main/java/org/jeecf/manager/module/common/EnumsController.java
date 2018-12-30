package org.jeecf.manager.module.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = { "common/enums" })
@Api(value = "common api", tags = { "系统数据源接口" })
public class EnumsController {
	
	@PostMapping(value = { "list" })
	@ResponseBody
	@ApiOperation(value = "列表", notes = "查询枚举列表")
	public JsonNode list() {
		
		return null;
		
	}

}
