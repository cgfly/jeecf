package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.model.AuthModelResult;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;
import org.jeecf.manager.module.config.service.SysNamespaceService;
import org.jeecf.manager.module.config.service.SysUserNamespaceService;
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
 * 命令行命名空间接口
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/namespace" })
@Api(value = "命令行命名空间 api", tags = { "命令行命名空间接口" })
public class NamespaceController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private SysNamespaceService sysNamespaceService;

    @Autowired
    private SysUserNamespaceService sysUserNamespaceService;

    @PostMapping(value = { "" })
    @ApiOperation(value = "当前数据", notes = "返回用户当前命名空间名称")
    public Response<String> get(@RequestBody AuthModel authModel) {
        Response<SysUserResult> sysUserRes = userAuthService.auth(authModel);
        SysNamespace sysNamespace = NamespaceUtils.getNamespace(sysUserRes.getData().getId());
        if (sysNamespace != null) {
            return new Response<>(sysNamespace.getName());
        }
        return new Response<>("");
    }

    @PostMapping(value = { "list" })
    @ApiOperation(value = "列表", notes = "返回用户命名空间列表")
    public Response<List<String>> list(@RequestBody AuthModel authModel) {
        Response<Set<String>> permissionsRes = userAuthService.getPremission(authModel);
        List<String> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissionsRes.getData())) {
            SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
            SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
            Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
            List<SysNamespaceResult> sysNamespaceResultList = sysNamespaceResultListRes.getData();
            if (CollectionUtils.isNotEmpty(sysNamespaceResultList)) {
                sysNamespaceResultList.forEach(sysNamespace -> {
                    permissionsRes.getData().forEach(premission -> {
                        if (sysNamespace.getPermission().equals(premission)) {
                            result.add(sysNamespace.getName());
                        }
                    });
                });
            }
        }
        return new Response<>(result);
    }

    @PostMapping(value = { "effect/{name}" })
    @ApiOperation(value = "生效", notes = "切换命名空间")
    public Response<Integer> effect(@RequestBody AuthModel authModel, @PathVariable String name) {
        Response<AuthModelResult> authModelResultRes = userAuthService.getDetailInfo(authModel);
        SysUserResult sysUser = authModelResultRes.getData().getSysUserResult();
        Set<String> permissions = authModelResultRes.getData().getPermissions();
        if (CollectionUtils.isNotEmpty(permissions)) {
            SysNamespaceQuery sysNamespaceQuery = new SysNamespaceQuery();
            sysNamespaceQuery.setName(name);
            SysNamespacePO sysNamespacePO = new SysNamespacePO(sysNamespaceQuery);
            Response<List<SysNamespaceResult>> sysNamespaceResultListRes = sysNamespaceService.findList(sysNamespacePO);
            List<SysNamespaceResult> sysNamespaceResultList = sysNamespaceResultListRes.getData();
            if (CollectionUtils.isNotEmpty(sysNamespaceResultList)) {
                SysNamespaceResult sysNamespaceResult = sysNamespaceResultList.get(0);
                for (String permission : permissions) {
                    if (sysNamespaceResult.getPermission().equals(permission)) {
                        return sysUserNamespaceService.updateByUserId(sysUser.getId(), Integer.valueOf(sysNamespaceResult.getId()));
                    }
                }
                throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
            }
            throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

}
