package org.jeecf.manager.module.extend.model.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.constraints.English;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModelProperty;

/**
 * OSGI 插件实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{plugin.name.isEmpty}", groups = { Add.class }),
        @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.pluginType)", message = "{plugin.pluginType.isEmpty}", groups = { Add.class }),
        @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.description)", message = "{plugin.description.isEmpty}", groups = { Add.class }) })
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
     * 插件类型
     */
    @ApiModelProperty(value = "插件类型", name = "pluginType")
    private Integer boundleType;
    /**
     * wiki地址
     */
    @ApiModelProperty(value = "wiki地址", name = "wikiUri")
    private String wikiUri;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    @Length(min = 1, max = 50, message = "{plugin.name.length}", groups = { Add.class })
    @English(message = "{plugin.name.english}",groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoundleType() {
        return boundleType;
    }

    public void setBoundleType(Integer boundleType) {
        this.boundleType = boundleType;
    }

    @Length(min = 0, max = 100, message = "{plugin.wikiUri.length}", groups = { Add.class })
    public String getWikiUri() {
        return wikiUri;
    }

    public void setWikiUri(String wikiUri) {
        this.wikiUri = wikiUri;
    }

    @Length(min = 1, max = 50, message = "{plugin.description.length}", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
