package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.template.model.po.GenFieldPO;
import org.jeecf.manager.module.template.model.query.GenFieldQuery;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.jeecf.manager.module.template.service.GenFieldService;
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
    public Response<List<String>> list(@RequestBody AuthModel authModel, @PathVariable String namespace) {
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(namespace);
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            List<String> fieldList = new ArrayList<>();
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(authModel, sysNamespaceResult.getPermission());
            GenFieldQuery genFieldQuery = new GenFieldQuery();
            genFieldQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
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

    @PostMapping(value = { "detail/{namespace}/{name}" })
    @ApiOperation(value = "详情", notes = "返回用户模版属性详情")
    public Response<GenFieldResult> detail(@RequestBody AuthModel authModel, @PathVariable String namespace, @PathVariable String name) {
        SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
        sysNamespaceQuery.setName(namespace);
        SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
        Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
        if (CollectionUtils.isNotEmpty(sysNamespaceResultListRes.getData())) {
            SysNamespaceResult sysNamespaceResult = sysNamespaceResultListRes.getData().get(0);
            userAuthService.auth(authModel, sysNamespaceResult.getPermission());
            GenFieldQuery genFieldQuery = new GenFieldQuery();
            genFieldQuery.setSysNamespaceId(Integer.valueOf(sysNamespaceResult.getId()));
            genFieldQuery.setName(name);
            GenFieldPO genFieldPO = new GenFieldPO(genFieldQuery);
            Response<List<GenFieldResult>> genFieldResultListRes = genFieldService.findList(genFieldPO);
            if (CollectionUtils.isNotEmpty(genFieldResultListRes.getData())) {
                return new Response<>(genFieldResultListRes.getData().get(0));
            }
            throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

}
