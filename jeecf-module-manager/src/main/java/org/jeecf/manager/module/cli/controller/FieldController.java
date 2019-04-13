package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.jeecf.manager.module.template.service.GenFieldService;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 命令行模版属性接口 controller
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/field" })
@Api(value = "命令行模版属性 api", tags = { "命令行模版属性接口" })
public class FieldController {

    @Autowired
    SysNamespaceService sysNamespaceService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private GenFieldService genFieldService;

    @PostMapping(value = { "list/{namespace}" })
    @ApiOperation(value = "列表", notes = "返回用户模版属性列表")
    public Response<List<String>> list(@RequestBody AuthModel authModel, @PathVariable(required = false) String namespace) {
        Response<SysUserResult> sysUserResultRes = userAuthService.auth(authModel);
        SysNamespace sysNamespace = sysNamespaceService.get(sysUserResultRes.getData().getId(), namespace);
        if (sysNamespace != null) {
            List<String> fieldList = new ArrayList<>();
            userAuthService.authPermission(sysUserResultRes.getData().getId(), sysNamespace.getPermission());
            GenFieldQuery genFieldQuery = new GenFieldQuery();
            genFieldQuery.setSysNamespaceId(Integer.valueOf(sysNamespace.getId()));
            GenFieldPO genFieldPO = new GenFieldPO(genFieldQuery);
            Response<List<GenFieldResult>> genFieldResultListRes = genFieldService.findList(genFieldPO);
            if (CollectionUtils.isNotEmpty(genFieldResultListRes.getData())) {
                genFieldResultListRes.getData().forEach(genFieldResult -> {
                    fieldList.add(genFieldResult.getName());
                });
            }
            return new Response<>(fieldList);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    @PostMapping(value = { "detail/{namespace}/{name}", "detail/{name}" })
    @ApiOperation(value = "详情", notes = "返回用户模版属性详情")
    public Response<GenFieldResult> detail(@RequestBody AuthModel authModel, @PathVariable(required = false) String namespace, @PathVariable String name) {
        Response<SysUserResult> sysUserResultRes = userAuthService.auth(authModel);
        SysNamespace sysNamespace = sysNamespaceService.get(sysUserResultRes.getData().getId(), namespace);
        if (sysNamespace != null) {
            userAuthService.authPermission(sysUserResultRes.getData().getId(), sysNamespace.getPermission());
            GenFieldQuery genFieldQuery = new GenFieldQuery();
            genFieldQuery.setSysNamespaceId(Integer.valueOf(sysNamespace.getId()));
            genFieldQuery.setName(name);
            GenFieldPO genFieldPO = new GenFieldPO(genFieldQuery);
            Response<List<GenFieldResult>> genFieldResultListRes = genFieldService.findList(genFieldPO);
            return new Response<>(genFieldResultListRes.getData().get(0));
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

}
