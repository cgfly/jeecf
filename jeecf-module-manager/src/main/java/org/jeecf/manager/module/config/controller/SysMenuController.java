package org.jeecf.manager.module.config.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.config.model.domain.SysMenu;
import org.jeecf.manager.module.config.model.po.SysMenuPO;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.result.SysMenuResult;
import org.jeecf.manager.module.config.model.schema.SysMenuSchema;
import org.jeecf.manager.module.config.service.SysMenuService;
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
 * 系统菜单
 * 
 * @author GloryJian
 *
 */
@Controller
@RequestMapping(value = { "config/sysMenu" })
@Api(value = "sysMenu api", tags = { "系统菜单接口" })
public class SysMenuController implements CurdController<SysMenuQuery, SysMenuResult, SysMenuSchema, SysMenu> {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysMenu.view}")
    @ApiOperation(value = "视图", notes = "查看系统菜单视图")
    @Override
    public String index(ModelMap map) {
        return "module/config/sysMenu";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.sysMenu.view}")
    @ApiOperation(value = "列表", notes = "查询系统菜单列表")
    @Override
    public Response<List<SysMenuResult>> list(@RequestBody Request<SysMenuQuery, SysMenuSchema> sysMenuQuery) {
        return sysMenuService.getTreeData(new SysMenuPO(sysMenuQuery));
    }

    @PostMapping(value = { "getTreeData" })
    @ResponseBody
    @RequiresPermissions("${permission.sysMenu.view}")
    @ApiOperation(value = "列表", notes = "查询系统菜单树表格列表")
    public Response<List<SysMenuResult>> getTreeData(SysMenuQuery sysMenuQuery) {
        return sysMenuService.getTreeData(new SysMenuPO(sysMenuQuery));
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysMenu.edit}")
    @ApiOperation(value = "更新", notes = "更新系统菜单数据")
    @Override
    public Response<SysMenuResult> save(@RequestBody @Validated({ Add.class }) SysMenu sysMenu) {
        if (sysMenu.isNewRecord()) {
            SysMenuQuery query = new SysMenuQuery();
            query.setLabel(sysMenu.getLabel());
            List<SysMenuResult> sysMenuList = sysMenuService.findList(new SysMenuPO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysMenuList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
        }
        return sysMenuService.save(sysMenu);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysMenu.edit}")
    @ApiOperation(value = "删除", notes = "删除系统菜单数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysMenuService.delete(new SysMenu(id));
    }

}