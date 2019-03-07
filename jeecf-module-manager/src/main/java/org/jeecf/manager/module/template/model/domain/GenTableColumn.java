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
 * gen表字段
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{genTableColumn.name.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.sort)", message = "{genTableColumn.sort.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.comment)", message = "{genTableColumn.comment.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.jdbcType)", message = "{genTableColumn.jdbcType.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.field)", message = "{genTableColumn.field.isEmpty}", groups = { Add.class }) 
})
@ApiModel(value = "genTableColumn", description = "代码生成业务字段表实体")
public class GenTableColumn extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务表
     */
    @ApiModelProperty(value = "业务表", name = "genTable")
    private GenTable genTable;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    /**
     * 是否空值
     */
    @ApiModelProperty(value = "空值", name = "isNull")
    private Integer isNull;

    /**
     * 字段排序
     */
    @ApiModelProperty(value = "字段排序", name = "sort")
    private Integer sort;

    /**
     * 是否主键
     */
    @ApiModelProperty(value = "是否主键", name = "isKey")
    private Integer isKey;

    /**
     * 注释
     */
    @ApiModelProperty(value = "注释", name = "comments")
    private String comment;

    /**
     * jdbc 类型
     */
    @ApiModelProperty(value = "jdbc 类型", name = "jdbcType")
    private String jdbcType;

    /**
     * 表单类型
     */
    @ApiModelProperty(value = "表单 类型", name = "formType")
    private Integer formType;

    /**
     * 属性
     */
    @ApiModelProperty(value = "属性", name = "javaField")
    private String field;

    /**
     * 是否为插入字段（1：插入字段）
     */
    @ApiModelProperty(value = "是否插入", name = "isInsert")
    private Integer isInsert;

    /**
     * 是否编辑字段（1：编辑字段）
     */
    @ApiModelProperty(value = "是否修改", name = "isEdit")
    private Integer isEdit;

    /**
     * 是否列表字段（1：列表字段）
     */
    @ApiModelProperty(value = "是否列表展现", name = "isList")
    private Integer isList;

    /**
     * 是否查询字段（1：查询字段）
     */
    @ApiModelProperty(value = "是否查询字段", name = "isQuery")
    private Integer isQuery;

    /**
     * 查询类型
     */
    @ApiModelProperty(value = "查询类型", name = "queryType")
    private Integer queryType;

    @Length(min = 1, max = 20, message = "{genTableColumn.name.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z_]*[a-zA-Z]$", message = "{genTableColumn.name.pattern}", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 0, message = "{genTableColumn.isNull.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isNull.max}", groups = { Add.class })
    public Integer getIsNull() {
        return isNull;
    }

    public void setIsNull(Integer isNull) {
        this.isNull = isNull;
    }

    @Min(value = 1, message = "{genTableColumn.sort.min}", groups = { Add.class })
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(min = 1, max = 50, message = "{genTableColumn.comment.length}", groups = { Add.class })
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Length(min = 1, max = 50, message = "{genTableColumn.jdbcType.length}", groups = { Add.class })
    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    @Min(value = 0, message = "{genTableColumn.isKey.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isKey.max}", groups = { Add.class })
    public Integer getIsKey() {
        return isKey;
    }

    public void setIsKey(Integer isKey) {
        this.isKey = isKey;
    }

    @Min(value = 0, message = "{genTableColumn.isInsert.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isInsert.max}", groups = { Add.class })
    public Integer getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(Integer isInsert) {
        this.isInsert = isInsert;
    }

    @Min(value = 0, message = "{genTableColumn.isEdit.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isEdit.max}", groups = { Add.class })
    public Integer getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    @Min(value = 0, message = "{genTableColumn.isList.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isList.max}", groups = { Add.class })
    public Integer getIsList() {
        return isList;
    }

    public void setIsList(Integer isList) {
        this.isList = isList;
    }

    @Min(value = 0, message = "{genTableColumn.isQuery.min}", groups = { Add.class })
    @Max(value = 1, message = "{genTableColumn.isQuery.max}", groups = { Add.class })
    public Integer getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }

    @Min(value = 0, message = "{genTableColumn.queryType.min}", groups = { Add.class })
    @Max(value = 9, message = "{genTableColumn.queryType.max}", groups = { Add.class })
    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public GenTable getGenTable() {
        return genTable;
    }

    public void setGenTable(GenTable genTable) {
        this.genTable = genTable;
    }

    @Length(min = 1, max = 50, message = "{genTableColumn.field.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{genTableColumn.field.pattern}", groups = { Add.class })
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Min(value = 0, message = "{genTableColumn.formType.min}", groups = { Add.class })
    @Max(value = 9, message = "{genTableColumn.formType.max}", groups = { Add.class })
    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

}
