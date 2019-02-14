package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.constraints.English;
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
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "名称输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)", message = "描述输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.genFieldColumn)", message = "属性列表输入不能为空", groups = { Add.class }) 
})
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

    @Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间", groups = { Add.class })
    @English(message = "名称必须为英文",groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 50, message = "描述长度必须介于 0 和 50 之间", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Valid
    @Size(min = 1, max = 30, message = "超过范围，最大可添加30个参数", groups = { Add.class })
    public List<GenFieldColumn> getGenFieldColumn() {
        return genFieldColumn;
    }

    public void setGenFieldColumn(List<GenFieldColumn> genFieldColumn) {
        this.genFieldColumn = genFieldColumn;
    }

}