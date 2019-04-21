package org.jeecf.manager.module.userpower.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.PermissionLabelEnum;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.po.SysPowerPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;
import org.jeecf.manager.module.userpower.model.schema.SysPowerSchema;
import org.jeecf.manager.module.userpower.service.SysPowerService;
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
 * 系统权限
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "userpower/sysPower" })
@Api(value = "sysPower api", tags = { "系统权限接口" })
public class SysPowerController implements CurdController<SysPowerQuery, SysPowerResult, SysPowerSchema, SysPower> {

    @Autowired
    private SysPowerService sysPowerService;

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysPower.view}")
    @ApiOperation(value = "视图", notes = "查看系统权限视图")
    @Override
    public String index(ModelMap map) {
        return "module/userpower/sysPower";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.sysPower.view}")
    @ApiOperation(value = "列表", notes = "查询系统权限列表")
    @Override
    public Response<List<SysPowerResult>> list(@RequestBody Request<SysPowerQuery, SysPowerSchema> request) {
        Response<List<SysPowerResult>> res = sysPowerService.getTreeData(new SysPowerPO(request));
        if (CollectionUtils.isNotEmpty(res.getData())) {
            res.getData().forEach(sysPower -> {
                sysPower.setLabelName(PermissionLabelEnum.getName(sysPower.getLabel()));
            });
        }
        return res;
    }

    @PostMapping(value = { "getTreeData" })
    @ResponseBody
    @RequiresPermissions("${permission.sysPower.view}")
    @ApiOperation(value = "列表", notes = "查询系统权限数表格列表")
    public Response<List<SysPowerResult>> getTreeData(SysPowerQuery sysPowerQuery) {
        return sysPowerService.getTreeData(new SysPowerPO(sysPowerQuery));
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysPower.edit}")
    @ApiOperation(value = "更新", notes = "更新系统权限数据")
    @Override
    public Response<SysPowerResult> save(@RequestBody @Validated({ Add.class }) SysPower sysPower) {
        if (sysPower.isNewRecord()) {
            SysPowerQuery query = new SysPowerQuery();
            query.setPermission(sysPower.getPermission());
            List<SysPowerResult> sysPowerList = sysPowerService.findList(new SysPowerPO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysPowerList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }
        }
        return sysPowerService.save(sysPower);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysPower.edit}")
    @ApiOperation(value = "删除", notes = "删除系统权限数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return securityFacade.deletePower(id);
    }

}