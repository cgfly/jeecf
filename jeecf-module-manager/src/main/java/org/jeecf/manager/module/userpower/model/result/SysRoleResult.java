package org.jeecf.manager.module.userpower.model.result;

import java.io.Serializable;
import java.util.List;

import org.jeecf.manager.module.userpower.model.domain.SysRole;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统角色返回实体
 * 
 * @author jianyiming
 *
 */
public class SysRoleResult extends SysRole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 权限列表
     */
    @ApiModelProperty(value = "权限列表", name = "sysPowerList")
    private List<SysPowerResult> sysPowerList;

    /**
     * 权限树结构列表
     */
    @ApiModelProperty(value = "权限树结构列表", name = "sysPowerTreeList")
    private List<SysPowerResult> sysPowerTreeList;
    /**
     * 选中
     */
    @ApiModelProperty(value = "选中", name = "checked")
    private boolean checked;

    public List<SysPowerResult> getSysPowerList() {
        return sysPowerList;
    }

    public void setSysPowerList(List<SysPowerResult> sysPowerList) {
        this.sysPowerList = sysPowerList;
    }

    public List<SysPowerResult> getSysPowerTreeList() {
        return sysPowerTreeList;
    }

    public void setSysPowerTreeList(List<SysPowerResult> sysPowerTreeList) {
        this.sysPowerTreeList = sysPowerTreeList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
