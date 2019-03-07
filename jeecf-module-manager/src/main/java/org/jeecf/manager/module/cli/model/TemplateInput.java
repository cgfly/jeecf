package org.jeecf.manager.module.cli.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版输入实体
 * 
 * @author jianyiming
 * @version 2.0
 */
@ApiModel(value = "templateInput", description = "模版输入实体")
public class TemplateInput extends AuthModel {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 属性
     */
    @ApiModelProperty(value = "属性", name = "field")
    private String field;
    /**
     * 语言
     */
    @ApiModelProperty(value = "语言", name = "language")
    private String language;
    /**
     * 命名空间
     */
    @ApiModelProperty(value = "命名空间", name = "namespace")
    private String namespace;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", name = "version")
    private String version;
    /**
     * wiki 地址
     */
    @ApiModelProperty(value = "wiki地址", name = "wikiUri")
    private String wikiUri;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWikiUri() {
        return wikiUri;
    }

    public void setWikiUri(String wikiUri) {
        this.wikiUri = wikiUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
