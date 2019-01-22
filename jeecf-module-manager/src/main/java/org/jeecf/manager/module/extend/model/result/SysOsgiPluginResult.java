package org.jeecf.manager.module.extend.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.extend.model.domain.SysOsgiPlugin;
import org.jeecf.osgi.enums.BoundleEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * OSGI 插件结果实体
 * 
 * @author jianyiming
 *
 */
public class SysOsgiPluginResult extends SysOsgiPlugin implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 插件类型名称
     */
    @ApiModelProperty(value = "插件类型名称", name = "pluginTypeName")
    public String boundleTypeName;

    public String getBoundleTypeName() {
        return boundleTypeName;
    }

    public void setBoundleTypeName(String boundleTypeName) {
        this.boundleTypeName = boundleTypeName;
    }

    public void toCovert() {
        if (this.getBoundleType() != null) {
            this.setBoundleTypeName(BoundleEnum.getName(this.getBoundleType()));
        }
    }

}
