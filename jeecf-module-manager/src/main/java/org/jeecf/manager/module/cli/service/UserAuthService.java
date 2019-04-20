package org.jeecf.manager.module.cli.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.SysEntrypt;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.jeecf.manager.module.cli.model.AuthModel;
import org.jeecf.manager.module.cli.model.AuthModelResult;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.manager.module.userpower.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户登录 权限验证
 * 
 * @author jianyiming
 * @version 2.0
 */
@Service
public class UserAuthService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    protected SecurityManager securityManager;

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<AuthModelResult> getDetailInfo(AuthModel authModel) {
        AuthModelResult modelResult = new AuthModelResult();
        SysUserQuery queryUser = new SysUserQuery();
        queryUser.setUsername(authModel.getUsername());
        SysUserPO queryPO = new SysUserPO(queryUser);
        Response<List<SysUserResult>> sysUserRes = sysUserService.findList(queryPO);
        List<SysUserResult> sysUserList = sysUserRes.getData();
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            SysUserResult sysUser = sysUserList.get(0);
            boolean isLogin = SysEntrypt.validatePassword(String.valueOf(authModel.getPassword()), sysUser.getPassword());
            if (isLogin) {
                Set<String> permissions = securityFacade.findPermission(sysUser.getId()).getData();
                modelResult.setPermissions(permissions);
                modelResult.setSysUserResult(sysUser);
                return new Response<>(modelResult);
            }
            throw new BusinessException(BusinessErrorEnum.USER_PASSWORD_ERROR);
        }
        throw new BusinessException(BusinessErrorEnum.USER_NOT);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<Set<String>> getPremission(AuthModel authModel) {
        SysUserQuery queryUser = new SysUserQuery();
        queryUser.setUsername(authModel.getUsername());
        SysUserPO queryPO = new SysUserPO(queryUser);
        Response<List<SysUserResult>> sysUserRes = sysUserService.findList(queryPO);
        List<SysUserResult> sysUserList = sysUserRes.getData();
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            SysUserResult sysUser = sysUserList.get(0);
            boolean isLogin = SysEntrypt.validatePassword(String.valueOf(authModel.getPassword()), sysUser.getPassword());
            if (isLogin) {
                return securityFacade.findPermission(sysUser.getId());
            }
            throw new BusinessException(BusinessErrorEnum.USER_PASSWORD_ERROR);
        }
        throw new BusinessException(BusinessErrorEnum.USER_NOT);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<SysUserResult> auth(AuthModel authModel) {
        SysUserQuery queryUser = new SysUserQuery();
        queryUser.setUsername(authModel.getUsername());
        SysUserPO queryPO = new SysUserPO(queryUser);
        Response<List<SysUserResult>> sysUserRes = sysUserService.findList(queryPO);
        List<SysUserResult> sysUserList = sysUserRes.getData();
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            SysUserResult sysUser = sysUserList.get(0);
            boolean isLogin = SysEntrypt.validatePassword(String.valueOf(authModel.getPassword()), sysUser.getPassword());
            if (isLogin) {
                return new Response<>(sysUser);
            }
            throw new BusinessException(BusinessErrorEnum.USER_PASSWORD_ERROR);
        }
        throw new BusinessException(BusinessErrorEnum.USER_NOT);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<SysUserResult> auth(AuthModel authModel, String validPermission) {
        Response<SysUserResult> sysUserResultRes = this.auth(authModel);
        Set<String> permissions = securityFacade.findPermission(sysUserResultRes.getData().getId()).getData();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                if (permission.equals(validPermission)) {
                    return sysUserResultRes;
                }
            }
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public void authPermission(String userId, String validPermission) {
        Set<String> permissions = securityFacade.findPermission(userId).getData();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                if (permission.equals(validPermission)) {
                    return;
                }
            }
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<String> auth(String id, String validPermission) {
        Set<String> permissions = securityFacade.findPermission(id).getData();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                if (permission.equals(validPermission)) {
                    return new Response<>(id);
                }
            }
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<String> auth(String id, List<String> validPermissions) {
        if(CollectionUtils.isEmpty(validPermissions)) {
            throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
        }
        int validCount = validPermissions.size();
        Set<String> permissions = securityFacade.findPermission(id).getData();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                for (String validPermission : validPermissions) {
                    if (permission.equals(validPermission)) {
                        validCount--;
                        break;
                    }
                }
            }
        }
        if(validCount == 0) {
            return new Response<>(id);
        }
        throw new BusinessException(SysErrorEnum.UNAUTHORIZED_ERROR);
    }

    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        Subject subject = new WebSubject.Builder(request, response).buildWebSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        subject.login(token);
        String id = (String) subject.getPrincipal();
        RedisCacheUtils.setSysCache((String) subject.getSession().getId(), id);
        ThreadContext.bind(subject);
        return id;
    }

}
