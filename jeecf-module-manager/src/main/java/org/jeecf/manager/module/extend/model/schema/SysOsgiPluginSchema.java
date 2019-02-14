package org.jeecf.manager.module.extend.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * OSGI 插件 schema
 * 
 * @author jianyiming
 *
 */
public class SysOsgiPluginSchema {
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
     * 插件绑定类型
     */
    @ApiModelProperty(value = "插件绑定类型", name = "boundleType")
    private boolean boundleType = true;
    /**
     * wiki地址
     */
    @ApiModelProperty(value = "wiki地址", name = "wikiUri")
    private boolean wikiUri = true;
    /**
     * 命名空间
     */
    @ApiModelProperty(value = "命名空间", name = "sysNamespaceId")
    private boolean sysNamespaceId = true;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private boolean description = true;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除", name = "delFlag")
    private boolean delFlag = true;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createDate")
    private boolean createDate = true;
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

    public boolean isBoundleType() {
        return boundleType;
    }

    public void setBoundleType(boolean boundleType) {
        this.boundleType = boundleType;
    }

    public boolean isWikiUri() {
        return wikiUri;
    }

    public void setWikiUri(boolean wikiUri) {
        this.wikiUri = wikiUri;
    }

    public boolean isSysNamespaceId() {
        return sysNamespaceId;
    }

    public void setSysNamespaceId(boolean sysNamespaceId) {
        this.sysNamespaceId = sysNamespaceId;
    }

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isCreateDate() {
        return createDate;
    }

    public void setCreateDate(boolean createDate) {
        this.createDate = createDate;
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
