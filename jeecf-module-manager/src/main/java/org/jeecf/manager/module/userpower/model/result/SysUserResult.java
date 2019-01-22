package org.jeecf.manager.module.userpower.model.result;

import java.io.Serializable;
import java.util.List;

import org.jeecf.manager.module.config.model.domain.SysOffice;
import org.jeecf.manager.module.userpower.model.domain.SysUser;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户返回实体
 * 
 * @author jianyiming
 *
 */
public class SysUserResult extends SysUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色名称
     */
    private String roleNames;

    /********************* 追加字段 ***************************/

    /**
     * 组织结构
     */
    private SysOffice sysOffice;
    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表", name = "sysRoleList")
    private List<SysRoleResult> sysRoleList;

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public List<SysRoleResult> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRoleResult> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public SysOffice getSysOffice() {
        return sysOffice;
    }

    public void setSysOffice(SysOffice sysOffice) {
        this.sysOffice = sysOffice;
    }

}
