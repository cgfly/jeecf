package org.jeecf.manager.module.config.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.AbstractTreePermissionEntity;
import org.jeecf.manager.module.config.model.result.SysMenuResult;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统菜单
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.label)", message = "{menu.label.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.route)", message = "{menu.route.isEmpty}", groups = { Add.class })
})
@ApiModel(value = "sysMenu", description = "系统菜单实体")
public class SysMenu extends AbstractTreePermissionEntity<SysMenuResult> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", name = "label")
    private String label;

    /**
     * 模块标签
     */
    @ApiModelProperty(value = "模块菜单标签", name = "moduleLabel")
    private String moduleLabel;

    /**
     * 路由
     */
    @ApiModelProperty(value = "路由", name = "route")
    private Integer route;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标", name = "icon")
    private String icon;

    /**
     * 是否显示图标
     */
    @ApiModelProperty(value = "是否显示图标", name = "isIcon")
    private Integer isIcon;

    public SysMenu() {
        super();
    }

    public SysMenu(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "{menu.label.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{menu.label.pattern}", groups = { Add.class })
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(min = 1, max = 20, message = "{menu.moduleLabel.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{menu.moduleLabel.pattern}", groups = { Add.class })
    public String getModuleLabel() {
        return moduleLabel;
    }

    public void setModuleLabel(String moduleLabel) {
        this.moduleLabel = moduleLabel;
    }

    @Min(value = 0, message = "{menu.route.min}", groups = { Add.class })
    @Max(value = 1, message = "{menu.route.max}", groups = { Add.class })
    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsIcon() {
        return isIcon;
    }

    public void setIsIcon(Integer isIcon) {
        this.isIcon = isIcon;
    }

    @Override
    public void sortList(List<SysMenuResult> newList, List<SysMenuResult> sourceList, String rootId) {
        for (int i = 0; i < sourceList.size(); i++) {
            SysMenuResult sysMenu = sourceList.get(i);
            if (("0".equals(rootId) && StringUtils.isBlank(sysMenu.getParentId())) || (sysMenu.getParentId() != null && sysMenu.getParentId().equals(rootId))) {

                newList.add(sysMenu);
                if (sysMenu.getLabel() != null && sysMenu.getLabel().equals(sysMenu.getModuleLabel())) {
                    sysMenu.setRouteName(sysMenu.getModuleLabel());
                }
                if (sysMenu.getRoute() == 1) {
                    sysMenu.setRouteName(sysMenu.getModuleLabel() + "@" + sysMenu.getLabel());
                }
                for (int j = 0; j < sourceList.size(); j++) {
                    SysMenu child = sourceList.get(j);
                    if (child.getParentId() != null && String.valueOf(child.getParentId()).equals(sysMenu.getId())) {
                        sysMenu.setHasChild(true);
                        sortList(newList, sourceList, sysMenu.getId());
                        break;
                    }
                }
            }
        }
    }

}