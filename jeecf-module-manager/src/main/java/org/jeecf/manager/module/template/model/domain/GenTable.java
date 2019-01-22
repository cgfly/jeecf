package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.common.utils.HumpUtils;
import org.jeecf.manager.common.model.NamespaceAndDbAuthEntity;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务表实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "表名输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.className)", message = "类名输入不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.genTableColumns)", message = "表字段输入不能为空", groups = { Add.class }) 
})
@ApiModel(value = "genTable", description = "代码生成业务表实体")
public class GenTable extends NamespaceAndDbAuthEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名", name = "className")
    private String className;

    /**
     * 注释
     */
    @ApiModelProperty(value = "注释", name = "comments")
    private String comment;

    /**
     * 父表id
     */
    @ApiModelProperty(value = "父表id", name = "parentTable")
    private String parentTableId;

    /**
     * 父表外键
     */
    @ApiModelProperty(value = "父表外键", name = "parentTableFk")
    private String parentTableFk;

    /**
     * 表字段
     */
    @ApiModelProperty(value = "表字段", name = "genTableColumns")
    private List<GenTableColumnResult> genTableColumns;

    public GenTable() {
    }

    public GenTable(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "表名长度必须介于 1 和 20 之间", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z_]*[a-zA-Z]$", message = "表名只能由a-zA-Z_组成", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            name = StringUtils.lowerCase(HumpUtils.humpToLine2(name));
        }
        this.name = name;
    }

    @Length(min = 1, max = 20, message = "类名称长度必须介于 1 和 20 之间", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+$", message = "类名称只能由a-zA-Z组成", groups = { Add.class })
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Length(min = 1, max = 50, message = "注释长度必须介于 1 和 50 之间", groups = { Add.class })
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Valid
    @Size(min = 1, max = 30, message = "表字段超过范围，至少一个，最大可添加30个参数", groups = { Add.class })
    public List<GenTableColumnResult> getGenTableColumns() {
        return genTableColumns;
    }

    public void setGenTableColumns(List<GenTableColumnResult> genTableColumns) {
        this.genTableColumns = genTableColumns;
    }

    @Length(min = 1, max = 11, message = "名称长度必须介于 1 和 11 之间", groups = { Add.class })
    public String getParentTableId() {
        return parentTableId;
    }

    public void setParentTableId(String parentTableId) {
        this.parentTableId = parentTableId;
    }

    @Length(min = 1, max = 10, message = "名称长度必须介于 1 和 10 之间", groups = { Add.class })
    public String getParentTableFk() {
        return parentTableFk;
    }

    public void setParentTableFk(String parentTableFk) {
        this.parentTableFk = parentTableFk;
    }

}
