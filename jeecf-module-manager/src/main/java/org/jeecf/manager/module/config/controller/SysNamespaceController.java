package org.jeecf.manager.module.config.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecf.common.enums.DelFlagEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.controller.CurdController;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.PermissionLabelEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.po.SysUserNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.model.result.SysUserNamespaceResult;
import org.jeecf.manager.module.config.model.schema.SysNamespaceSchema;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.config.service.SysUserNamespaceService;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
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
 * 系统命名空间
 * 
 * @author GloryJian
 * @version 1.0
 */
@Controller
@RequestMapping(value = { "config/sysNamespace" })
@Api(value = "sysNamespace api", tags = { "系统命名空间接口" })
public class SysNamespaceController implements CurdController<SysNamespaceQuery, SysNamespaceResult, SysNamespaceSchema, SysNamespace> {

    @Autowired
    private SysNamespaceService sysNamespaceService;

    @Autowired
    private SysUserNamespaceService sysUserNamespaceService;

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping(value = { "", "index" })
    @RequiresPermissions("${permission.sysNamespace.view}")
    @ApiOperation(value = "视图", notes = "查看系统命名空间视图")
    @Override
    public String index(ModelMap map) {
        return "module/config/sysNamespace";
    }

    @PostMapping(value = { "list" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.view}")
    @ApiOperation(value = "列表", notes = "查询系统命名空间列表")
    @Override
    public Response<List<SysNamespaceResult>> list(@RequestBody Request<SysNamespaceQuery, SysNamespaceSchema> request) {
        return sysNamespaceService.findPageByAuth(new SysNamespacePO(request));
    }

    @PostMapping(value = { "save" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.edit}")
    @ApiOperation(value = "更新", notes = "更新系统命名空间数据")
    @Override
    public Response<SysNamespaceResult> save(@RequestBody @Validated({ Add.class }) SysNamespace sysNamespace) {
        if (sysNamespace.isNewRecord()) {
            SysNamespaceQuery query = new SysNamespaceQuery();
            query.setName(sysNamespace.getName());
            List<SysNamespaceResult> sysNamespaceList = sysNamespaceService.findList(new SysNamespacePO(query)).getData();
            if (CollectionUtils.isNotEmpty(sysNamespaceList)) {
                throw new BusinessException(BusinessErrorEnum.DATA_EXIT);
            }

        }
        return sysNamespaceService.saveByAuth(sysNamespace);
    }

    @PostMapping(value = { "invalid/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.edit}")
    @ApiOperation(value = "删除", notes = "删除系统命名空间数据")
    public Response<SysNamespaceResult> invalid(@PathVariable("id") String id) {
        Integer currentId = NamespaceUtils.getNamespaceId();
        if (currentId.equals(Integer.valueOf(id))) {
            throw new BusinessException(BusinessErrorEnum.NAMESPACE_IS_CURRENT);
        }
        SysNamespace sysNamespace = new SysNamespace(id);
        sysNamespace.setDelFlag(DelFlagEnum.YES.getCode());
        return sysNamespaceService.saveByAuth(sysNamespace);
    }

    @PostMapping(value = { "active/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.edit}")
    @ApiOperation(value = "激活", notes = "激活系统命名空间数据")
    public Response<Integer> active(@PathVariable("id") String id) {
        SysNamespace sysNamespace = new SysNamespace(id);
        sysNamespace.setDelFlag(DelFlagEnum.NO.getCode());
        sysNamespaceService.saveByAuth(sysNamespace);
        return new Response<>(1);
    }

    @PostMapping(value = { "effect/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.view}")
    @ApiOperation(value = "生效", notes = "生效选中命名空间")
    public Response<SysUserNamespaceResult> effect(@PathVariable String id) {
        String userId = UserUtils.getCurrentUserId();
        SysUserNamespaceQuery sysUserNamespace = new SysUserNamespaceQuery();
        sysUserNamespace.setUserId(userId);
        List<SysUserNamespaceResult> userNamespaceList = sysUserNamespaceService.findList(new SysUserNamespacePO(sysUserNamespace)).getData();
        SysUserNamespace namespace = null;
        if (CollectionUtils.isNotEmpty(userNamespaceList)) {
            namespace = userNamespaceList.get(0);
        } else {
            namespace = new SysUserNamespace();
            namespace.setUserId(userId);
        }
        namespace.setNamespaceId(Integer.valueOf(id));
        return sysUserNamespaceService.save(namespace);
    }

    @PostMapping(value = { "delete/{id}" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.edit}")
    @ApiOperation(value = "删除", notes = "删除命名空间数据")
    @Override
    public Response<Integer> delete(@PathVariable("id") String id) {
        return sysNamespaceService.deleteByAuth(new SysNamespace(id));
    }

    @PostMapping(value = { "permissions" })
    @ResponseBody
    @RequiresPermissions("${permission.sysNamespace.edit}")
    @ApiOperation(value = "权限", notes = "获取命名空间权限")
    public Response<List<Map<String, String>>> permissions() {
        List<Map<String, String>> result = new ArrayList<>();
        Response<Set<String>> res = securityFacade.findPermission(UserUtils.getCurrentUserId(), PermissionLabelEnum.NAMESPACE.getCode());
        if (CollectionUtils.isNotEmpty(res.getData())) {
            res.getData().forEach(data -> {
                Map<String, String> resultMap = new LinkedHashMap<>();
                resultMap.put("code", data);
                resultMap.put("name", data);
                result.add(resultMap);
            });
        }
        return new Response<>(result).copyValue(res);
    }

}