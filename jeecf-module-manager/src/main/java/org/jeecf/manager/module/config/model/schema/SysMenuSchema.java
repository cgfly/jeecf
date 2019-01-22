package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统菜单 schema
 * 
 * @author jianyiming
 *
 */
public class SysMenuSchema {
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
     * 权限
     */
    @ApiModelProperty(value = "权限", name = "permission")
    private boolean permission = true;

    /**
     * 父层
     */
    @ApiModelProperty(value = "父层", name = "parent")
    private boolean parent = true;

    /**
     * 父层索引
     */
    @ApiModelProperty(value = "父层索引", name = "parentId")
    private boolean parentId = true;

    /**
     * 父层索引集
     */
    @ApiModelProperty(value = "所有父层索引", name = "parentIds")
    private boolean parentIds = true;

    /**
     * 等级
     */
    @ApiModelProperty(value = "等级", name = "level")
    private boolean level = true;

    /**
     * 同级排序
     */
    @ApiModelProperty(value = "同级排序", name = "sort")
    private boolean sort = true;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", name = "label")
    private boolean label = true;

    /**
     * 模块菜单标签
     */
    @ApiModelProperty(value = "模块菜单标签", name = "moduleLabel")
    private boolean moduleLabel = true;

    /**
     * 路由
     */
    @ApiModelProperty(value = "路由", name = "route")
    private boolean route = true;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", name = "icon")
    private boolean icon = true;

    /**
     * 是否显示图标
     */
    @ApiModelProperty(value = "是否显示图标", name = "isIcon")
    private boolean isIcon = true;
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

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isParentId() {
        return parentId;
    }

    public void setParentId(boolean parentId) {
        this.parentId = parentId;
    }

    public boolean isParentIds() {
        return parentIds;
    }

    public void setParentIds(boolean parentIds) {
        this.parentIds = parentIds;
    }

    public boolean isLevel() {
        return level;
    }

    public void setLevel(boolean level) {
        this.level = level;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isLabel() {
        return label;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public boolean isModuleLabel() {
        return moduleLabel;
    }

    public void setModuleLabel(boolean moduleLabel) {
        this.moduleLabel = moduleLabel;
    }

    public boolean isRoute() {
        return route;
    }

    public void setRoute(boolean route) {
        this.route = route;
    }

    public boolean isIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }

    public boolean getIsIcon() {
        return isIcon;
    }

    public void setIsIcon(boolean isIcon) {
        this.isIcon = isIcon;
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

}
