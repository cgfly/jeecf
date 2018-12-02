package org.jeecf.manager.module.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.jeecf.manager.module.config.model.po.SysMenuPO;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.result.SysMenuResult;
import org.jeecf.manager.module.config.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 主页面 controller
 * @author jianyiming
 *
 */
@Controller
@Api(value="main api",tags={"主页面视图"})
public class MainController {
	
	@Autowired
	private SysMenuService sysMenuService;
	

	
	@ModelAttribute("sysMenus")
	public List<SysMenuResult> messages(HttpServletRequest req) {
		SysMenuQuery queryMenu = new SysMenuQuery();
        List<SysMenuResult> sysMenuList = sysMenuService.getTreeData(new SysMenuPO(queryMenu), true).getData();
        sysMenuList = PermissionUtils.filter(sysMenuList);
		return sysMenuList;
	}
	
	@GetMapping(value= {"","index"})
	@ApiOperation(value = "视图", notes = "查看主页面视图")
	public String index(HttpServletRequest req) {
		return "main/index";
	}
	
	@PostMapping(value= {"route"})
	@ResponseBody
	@ApiOperation(value = "列表", notes = "查询菜单路由列表")
	public Response<List<SysMenuResult>> route(HttpServletRequest req) {
		SysMenuQuery queryMenu = new SysMenuQuery();
        List<SysMenuResult> sysMenuList = sysMenuService.getTreeData(new SysMenuPO(queryMenu), true).getData();
        sysMenuList = PermissionUtils.filter(sysMenuList);
		return new Response<List<SysMenuResult>>(sysMenuList);
	}
	
}
