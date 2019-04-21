package org.jeecf.manager.module.userpower.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.userpower.model.domain.SysPower;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统权限返回实体
 * 
 * @author jianyiming
 *
 */
public class SysPowerResult extends SysPower implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*************** 追加字段 *********************/
    /**
     * 选中
     */
    @ApiModelProperty(value = "选中", name = "checked")
    private boolean checked = false;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", name = "labelName")
    private String labelName;

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

}
