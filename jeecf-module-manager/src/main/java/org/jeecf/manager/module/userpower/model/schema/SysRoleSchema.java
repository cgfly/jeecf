package org.jeecf.manager.module.userpower.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统角色 schema
 * 
 * @author jianyiming
 *
 */
public class SysRoleSchema {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private boolean id = true;
    /**
     * 中文名
     */
    @ApiModelProperty(value = "中文名", name = "name")
    private boolean name = true;

    /**
     * 英文名
     */
    @ApiModelProperty(value = "英文名", name = "enname")
    private boolean enname = true;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark")
    private boolean remark = true;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createDate")
    private boolean createDate = true;

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isEnname() {
        return enname;
    }

    public void setEnname(boolean enname) {
        this.enname = enname;
    }

    public boolean isRemark() {
        return remark;
    }

    public void setRemark(boolean remark) {
        this.remark = remark;
    }

    public boolean isCreateDate() {
        return createDate;
    }

    public void setCreateDate(boolean createDate) {
        this.createDate = createDate;
    }

}
