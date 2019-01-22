package org.jeecf.manager.module.extend.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 虚表 schema
 * 
 * @author jianyiming
 *
 */
public class SysVirtualTableSchema {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private boolean id = true;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private boolean name = true;
    /**
     * 注释
     */
    @ApiModelProperty(value = "注释", name = "comments")
    private boolean comment = true;
    /**
     * 命名空间
     */
    @ApiModelProperty(value = "命名空间", name = "sysNamespace")
    private boolean sysNamespaceId = true;
    /**
     * 数据连接源
     */
    @ApiModelProperty(value = "数据连接源", name = "sysDbsource")
    private boolean sysDbsourceId = true;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "createBy")
    private boolean createBy = true;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateDate")
    private boolean updateDate = true;

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

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isSysNamespaceId() {
        return sysNamespaceId;
    }

    public void setSysNamespaceId(boolean sysNamespaceId) {
        this.sysNamespaceId = sysNamespaceId;
    }

    public boolean isSysDbsourceId() {
        return sysDbsourceId;
    }

    public void setSysDbsourceId(boolean sysDbsourceId) {
        this.sysDbsourceId = sysDbsourceId;
    }

    public boolean isCreateBy() {
        return createBy;
    }

    public void setCreateBy(boolean createBy) {
        this.createBy = createBy;
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

}
