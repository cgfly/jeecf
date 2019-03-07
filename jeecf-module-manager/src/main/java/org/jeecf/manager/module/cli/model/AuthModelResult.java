package org.jeecf.manager.module.cli.model;

import java.util.Set;

import org.jeecf.manager.module.userpower.model.result.SysUserResult;

/**
 * 用户验证 结果实体
 * 
 * @author jianyiming
 * @version 2.0
 */
public class AuthModelResult {
    /**
     * 用户信息
     */
    private SysUserResult sysUserResult;
    /**
     * 权限集合
     */
    private Set<String> permissions;

    public SysUserResult getSysUserResult() {
        return sysUserResult;
    }

    public void setSysUserResult(SysUserResult sysUserResult) {
        this.sysUserResult = sysUserResult;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}
