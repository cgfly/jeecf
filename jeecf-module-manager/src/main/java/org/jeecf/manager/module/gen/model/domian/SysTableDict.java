package org.jeecf.manager.module.gen.model.domian;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAndDbAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表组字典
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "名称输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.tableName)", message = "表名输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.field)", message = "属性输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)", message = "描述输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.comment)", message = "注释输入不能为空", groups = { Add.class }) 
})
@ApiModel(value = "sysTableDict", description = "系统表组字典实体")
public class SysTableDict extends NamespaceAndDbAuthEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SysTableDict() {
        super();
    }

    public SysTableDict(String id) {
        super(id);
    }

    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    @ApiModelProperty(value = "表名", name = "tableName")
    private String tableName;

    @ApiModelProperty(value = "属性", name = "field")
    private String field;

    @ApiModelProperty(value = "注释", name = "comment")
    private String comment;

    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    @Length(min = 1, max = 30, message = "名称长度必须介于 1 和 30 之间", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 30, message = "表名长度必须介于 1 和 30 之间", groups = { Add.class })
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Length(min = 1, max = 30, message = "属性长度必须介于 1 和 30 之间", groups = { Add.class })
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
