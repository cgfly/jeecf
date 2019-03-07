package org.jeecf.manager.module.cli.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.model.AuthModelResult;
import org.jeecf.manager.module.cli.service.UserAuthService;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;
import org.jeecf.manager.module.config.service.SysDbsourceService;
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
 * 命令行数据源接口
 * 
 * @author jianyiming
 * @version 2.0
 */
@RestController
@RequestMapping(value = { "cli/sysDbsource" })
@Api(value = "命令行数据源  api", tags = { "命令行数据源接口" })
public class DbsourceController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private SysDbsourceService sysDbsourceService;

    @PostMapping(value = { "" })
    @ApiOperation(value = "当前数据", notes = "返回用户当前数据源名称")
    public Response<String> get(@RequestBody AuthModel authModel) {
        Response<SysUserResult> sysUserRes = userAuthService.auth(authModel);
        String dbsourceKey = DynamicDataSourceContextHolder.getCurrentDataSourceValue(sysUserRes.getData().getId());
        return new Response<>(dbsourceKey);
    }

    @PostMapping(value = { "list" })
    @ApiOperation(value = "列表", notes = "返回用户数据源列表")
    public Response<List<String>> list(@RequestBody AuthModel authModel) {
        Response<Set<String>> permissionsRes = userAuthService.getPremission(authModel);
        List<String> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissionsRes.getData())) {
            SysDbsourceQuery sysDbsourceQuery = new SysDbsourceQuery();
            SysDbsourcePO sysDbsourcePO = new SysDbsourcePO(sysDbsourceQuery);
            Response<List<SysDbsourceResult>> dbsourceResultRes = sysDbsourceService.findList(sysDbsourcePO);
            if (CollectionUtils.isNotEmpty(dbsourceResultRes.getData())) {
                dbsourceResultRes.getData().forEach(dbsource -> {
                    permissionsRes.getData().forEach(premission -> {
                        if (dbsource.getPermission().equals(premission)) {
                            result.add(dbsource.getKeyName());
                        }
                    });
                });
            }
        }
        return new Response<>(result);
    }

    @PostMapping(value = { "effect/{name}" })
    @ApiOperation(value = "生效", notes = "切换数据源")
    public Response<Integer> effect(@RequestBody AuthModel authModel, @PathVariable String name) {
        Response<AuthModelResult> authModelResultRes = userAuthService.getDetailInfo(authModel);
        SysUserResult sysUser = authModelResultRes.getData().getSysUserResult();
        Set<String> permissions = authModelResultRes.getData().getPermissions();
        if (CollectionUtils.isNotEmpty(permissions)) {
            SysDbsourceQuery sysDbsourceQuery = new SysDbsourceQuery();
            sysDbsourceQuery.setKeyName(name);
            SysDbsourcePO sysDbsourcePO = new SysDbsourcePO(sysDbsourceQuery);
            Response<List<SysDbsourceResult>> dbsourceResultRes = sysDbsourceService.findList(sysDbsourcePO);
            List<SysDbsourceResult> sysDbsourceResultList = dbsourceResultRes.getData();
            if (CollectionUtils.isNotEmpty(sysDbsourceResultList)) {
                SysDbsourceResult sysDbsourceResult = sysDbsourceResultList.get(0);
                for (String permission : permissions) {
                    if (sysDbsourceResult.getPermission().equals(permission)) {
                        DynamicDataSourceContextHolder.setCurrentDataSourceValue(sysDbsourceResult.getKeyName(), sysUser.getId(), Integer.valueOf(sysDbsourceResult.getId()),
                                sysDbsourceResult.getUsable());
                        return new Response<>(1);
                    }
                }
                throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
            }
            throw new BusinessException(BusinessErrorEnum.DARASOURCE_NOT);
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

}
