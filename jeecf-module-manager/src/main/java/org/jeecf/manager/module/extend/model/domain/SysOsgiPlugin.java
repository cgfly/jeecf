package org.jeecf.manager.module.extend.model.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModelProperty;

/**
 * OSGI 插件实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "名称输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.fileName)", message = "文件输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.pluginType)", message = "插件类型输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.description)", message = "描述输入不能为空", groups = { Add.class }) 
})
public class SysOsgiPlugin extends NamespaceAuthEntity {

    public SysOsgiPlugin() {
    }

    public SysOsgiPlugin(String id) {
        super(id);
    }

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 文件名字
     */
    @ApiModelProperty(value = "文件名字", name = "fileName")
    private String fileName;
    /**
     * 插件类型
     */
    @ApiModelProperty(value = "插件类型", name = "pluginType")
    private Integer boundleType;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    @Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 50, message = "文件名字长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getBoundleType() {
        return boundleType;
    }

    public void setBoundleType(Integer boundleType) {
        this.boundleType = boundleType;
    }

    @Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
