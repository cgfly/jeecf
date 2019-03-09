package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版参数
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({
        @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{genField.name.isEmpty}", groups = { Add.class }),
        @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)", message = "{genField.description.isEmpty}", groups = {
                Add.class }),
        @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.genFieldColumn)", message = "{genField.fieldColumn.isNull}", groups = {
                Add.class }) })
@ApiModel(value = "genField", description = "模版参数实体")
public class GenField extends NamespaceAuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;
    /**
     * 模版参数列表
     */
    @ApiModelProperty(value = "模版参数列表实体", name = "genFieldColumn")
    private List<GenFieldColumn> genFieldColumn;

    public GenField() {
        super();
    }

    public GenField(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "{genField.name.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "{genField.name.pattern}", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 50, message = "{genField.description.length}", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Valid
    @Size(min = 1, max = 30, message = "{genField.fieldColumn.size}", groups = { Add.class })
    public List<GenFieldColumn> getGenFieldColumn() {
        return genFieldColumn;
    }

    public void setGenFieldColumn(List<GenFieldColumn> genFieldColumn) {
        this.genFieldColumn = genFieldColumn;
    }

}