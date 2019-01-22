package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版配置
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value = "genTemplate", description = "生成模版实体")
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "名称输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.language)", message = "语言输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.fileBasePath)", message = "模版文件基础路径输入不能为空", groups = { Add.class }) 
})
public class GenTemplate extends NamespaceAuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 语言
     */
    @ApiModelProperty(value = "语言", name = "language")
    private Integer language;
    /**
     * 关联gen_field
     */
    @ApiModelProperty(value = "关联gen_field", name = "genFieldId")
    private Integer genFieldId;
    /**
     * 模版文件基础路径
     */
    @ApiModelProperty(value = "模版文件基础路径", name = "fileBasePath")
    private String fileBasePath;
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

    public GenTemplate() {
        super();
    }

    public GenTemplate(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 1, message = "语言输入错误", groups = { Add.class })
    @Max(value = 2, message = "语言输入错误", groups = { Add.class })
    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getGenFieldId() {
        return genFieldId;
    }

    public void setGenFieldId(Integer genFieldId) {
        this.genFieldId = genFieldId;
    }

    @Length(min = 1, max = 100, message = "模版文件基础路径长度必须介于 1 和 100 之间", groups = { Add.class })
    public String getFileBasePath() {
        return fileBasePath;
    }

    public void setFileBasePath(String fileBasePath) {
        this.fileBasePath = fileBasePath;
    }

    @Length(min = 1, max = 100, message = "wiki地址长度必须介于 1 和 100 之间", groups = { Add.class })
    public String getWikiUri() {
        return wikiUri;
    }

    public void setWikiUri(String wikiUri) {
        this.wikiUri = wikiUri;
    }

    @Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}