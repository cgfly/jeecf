package org.jeecf.manager.module.config.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.config.model.domain.SysMenu;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统菜单返回实体
 * 
 * @author jianyiming
 *
 */
public class SysMenuResult extends SysMenu implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 路由名称
     */
    @ApiModelProperty(value = "路由名称", name = "routeName")
    private String routeName;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

}
