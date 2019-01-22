package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;

import org.jeecf.manager.common.model.BaseEntity;

/**
 * 系统角色权限
 * 
 * @author GloryJian
 * @version 1.0
 */
public class SysRolePower extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色索引
     */
    private SysRole sysRole;
    /**
     * 权限索引
     */
    private SysPower sysPower;

    public SysRolePower() {
        super();
    }

    public SysRolePower(String id) {
        super(id);
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysPower getSysPower() {
        return sysPower;
    }

    public void setSysPower(SysPower sysPower) {
        this.sysPower = sysPower;
    }

}