package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版参数列表
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{genFieldColumn.name.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)", message = "{genFieldColumn.description.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.isNull)", message = "{genFieldColumn.isNull.isNull}", groups = { Add.class }) 
})
@ApiModel(value = "genFieldColumn", description = "模版参数列表实体")
public class GenFieldColumn extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 关联gen_field
     */
    @ApiModelProperty(value = "关联gen_field", name = "genFieldId")
    private Integer genFieldId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 允许为空
     */
    @ApiModelProperty(value = "为空", name = "isNull")
    private Integer isNull;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    public GenFieldColumn() {
        super();
    }

    public GenFieldColumn(String id) {
        super(id);
    }

    public Integer getGenFieldId() {
        return genFieldId;
    }

    public void setGenFieldId(Integer genFieldId) {
        this.genFieldId = genFieldId;
    }

    @Length(min = 1, max = 20, message = "{genFieldColumn.name.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{genFieldColumn.name.pattern}", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 50, message = "{genFieldColumn.description.length}", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 0, message = "{genFieldColumn.isNull.min}", groups = { Add.class })
    @Max(value = 1, message = "{genFieldColumn.isNull.max}", groups = { Add.class })
    public Integer getIsNull() {
        return isNull;
    }

    public void setIsNull(Integer isNull) {
        this.isNull = isNull;
    }

    @Length(min = 0, max = 30, message = "{genFieldColumn.defaultValue.length}", groups = { Add.class })
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}