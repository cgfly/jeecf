package org.jeecf.manager.module.userpower.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.po.SysPowerPO;
import org.jeecf.manager.module.userpower.model.po.SysRolePO;
import org.jeecf.manager.module.userpower.model.po.SysRolePowerPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.query.SysRolePowerQuery;
import org.jeecf.manager.module.userpower.model.query.SysRoleQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;
import org.jeecf.manager.module.userpower.model.result.SysRolePowerResult;
import org.jeecf.manager.module.userpower.model.result.SysRoleResult;
import org.jeecf.manager.module.userpower.model.schema.SysRoleSchema;
import org.jeecf.manager.module.userpower.service.SysPowerService;
import org.jeecf.manager.module.userpower.service.SysRolePowerService;
import org.jeecf.manager.module.userpower.service.SysRoleService;
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
 * 系统角色
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "userpower/sysRole" })
@Api(value = "sysRole api", tags = { "系统角色接口" })
public class SysRoleController implements CurdController<SysRoleQuery, SysRoleResult, SysRoleSchema, SysRole> {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePowerService sysRolePowerService;

    @Autowired
    private SysPowerService sysPowerService;

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("userpower:sysRole:view")
    @ApiOperation(value = "视图", notes = "查看系统角色视图")
    @Override
    public String index(ModelMap map) {
        return "module/userpower/sysRole";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("userpower:sysRole:view")
    @ApiOperation(value = "列表", notes = "查询系统角色列表")
    @Override
    public Response<List<SysRoleResult>> list(@RequestBody Request<SysRoleQuery, SysRoleSchema> request) {
        return sysRoleService.findPage(new SysRolePO(request));
    }

    @PostMapping(value = { "getTree/{roleId}" })
    @ResponseBody
    @RequiresPermissions("userpower:sysRole:view")
    @ApiOperation(value = "列表", notes = "查询系统角色树结构列表")
    public Response<SysRoleResult> getTree(@PathVariable("roleId") String roleId) {
        SysRole queryRole = new SysRole(roleId);
        Response<SysRoleResult> sysRoleRes = sysRoleService.get(queryRole);
        if (sysRoleRes.isSuccess() && sysRoleRes.getData() != null) {
            SysRoleResult sysRole = sysRoleRes.getData();
            SysRolePowerQuery rolePower = new SysRolePowerQuery();
            queryRole.setId(sysRole.getId());
            rolePower.setSysRole(queryRole);
            Response<List<SysRolePowerResult>> rolePowerList = sysRolePowerService.findList(new SysRolePowerPO(rolePower));
            List<SysPower> sysPowerList = new ArrayList<>();
            rolePowerList.getData().forEach(power -> {
                sysPowerList.add(power.getSysPower());
            });
            List<SysPowerResult> sysPowerTreeList = sysPowerService.findList(new SysPowerPO(new SysPowerQuery())).getData();
            sysPowerTreeList.forEach(sysPowerTree -> {
                sysPowerList.forEach(sysPower -> {
                    if (sysPower.getId().equals(sysPowerTree.getId())) {
                        sysPowerTree.setChecked(true);
                    }
                });
            });
            sysRole.setSysPowerList(sysPowerTreeList);
        }
        return sysRoleRes;
    }

    @PostMapping(value = { "getAllTree" })
    @ResponseBody
    @RequiresPermissions("userpower:sysRole:view")
    @ApiOperation(value = "列表", notes = "查询系统权限列表")
    public Response<List<SysPowerResult>> getPowerTree() {
        return sysPowerService.findList(new SysPowerPO(new SysPowerQuery()));
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("userpower:sysRole:edit")
    @ApiOperation(value = "更新", notes = "更新系统角色数据")
    @Override
    public Response<SysRoleResult> save(@RequestBody @Validated({ Add.class }) SysRole sysRole) {
        if (sysRole.isNewRecord()) {
            SysRoleQuery query = new SysRoleQuery();
            query.setEnname(sysRole.getEnname());
            List<SysRoleResult> sysRoleList = sysRoleService.findList(new SysRolePO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysRoleList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
        }
        return securityFacade.saveRole(sysRole);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("userpower:sysRole:edit")
    @ApiOperation(value = "删除", notes = "删除系统角色数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return securityFacade.deleteRole(id);
    }

}