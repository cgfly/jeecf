package org.jeecf.manager.security.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.ResponseUtils;
import org.jeecf.common.utils.SysEntrypt;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.domain.SysRole;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.jeecf.manager.module.userpower.service.SysUserService;
import org.jeecf.manager.security.model.SysUserPrincipal;

/**
 * shiro 用户权限验证
 * 
 * @author jianyiming
 *
 */
public class SysShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SecurityFacade securityFacade = (SecurityFacade) SpringContextUtils.getBean("securityFacade");
        String principal = (String) principalCollection.getPrimaryPrincipal();
        Response<List<SysRole>> sysRoleRes = securityFacade.findRole(principal);
        Set<String> roleSet = new HashSet<>();
        Set<String> powerSet = new HashSet<String>();
        if (ResponseUtils.isNotEmpty(sysRoleRes)) {
            sysRoleRes.getData().forEach(sysRole -> {
                roleSet.add(sysRole.getEnname());
                Response<List<SysPower>> sysPowerRes = securityFacade.findPower(sysRole.getId());
                if (ResponseUtils.isNotEmpty(sysPowerRes)) {
                    sysPowerRes.getData().forEach(sysPower -> {
                        String afterLast = StringUtils.substringAfterLast(sysPower.getPermission(), ":");
                        if (PermissionUtils.MATCH_PERMISSION.equals(afterLast)) {
                            String beforeLast = StringUtils.substringBeforeLast(sysPower.getPermission(), ":");
                            boolean flag = true;
                            for (SysPower power : sysPowerRes.getData()) {
                                String tempBeforeLast = StringUtils.substringBeforeLast(power.getPermission(), ":");
                                if (tempBeforeLast.equals(beforeLast) && !power.getPermission().equals(sysPower.getPermission())) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                for (String value : PermissionUtils.RESOLVE_PERMISSION) {
                                    powerSet.add(beforeLast + SplitCharEnum.COLON.getName() + value);
                                }
                            }
                        } else {
                            powerSet.add(sysPower.getPermission());
                        }
                    });
                }
            });
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);
        info.setStringPermissions(powerSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        SysUserService sysUserService = (SysUserService) SpringContextUtils.getBean("sysUserService");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        SysUserQuery queryUser = new SysUserQuery();
        queryUser.setUsername((String) token.getPrincipal());
        SysUserPO queryPO = new SysUserPO(queryUser);
        Response<List<SysUserResult>> sysUserRes = sysUserService.findList(queryPO);
        List<SysUserResult> sysUserList = sysUserRes.getData();
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            SysUser sysUser = sysUserList.get(0);
            boolean isLogin = SysEntrypt.validatePassword(String.valueOf(token.getPassword()), sysUser.getPassword());
            if (isLogin) {
                SysUserPrincipal sysUserPrincipal = new SysUserPrincipal();
                sysUserPrincipal.setId(sysUser.getId());
                sysUserPrincipal.setUsername(sysUser.getUsername());
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUserPrincipal.getId(), token.getPassword(), ByteSource.Util.bytes(token.getPassword()), getName());
                String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
                if (StringUtils.isNotEmpty(sessionId)) {
                    RedisCacheUtils.setSysCache(sessionId, sysUserPrincipal.getId());
                }
                return authenticationInfo;
            }
        }
        return null;
    }

}
