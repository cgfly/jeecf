package org.jeecf.manager.module.template.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代码表列表 schema
 * 
 * @author jianyiming
 *
 */
public class GenTableColumnSchema {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private boolean id = true;
    /**
     * 业务表
     */
    @ApiModelProperty(value = "业务表", name = "genTable")
    private boolean genTable = true;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private boolean name = true;

    /**
     * 是否空值
     */
    @ApiModelProperty(value = "空值", name = "isNull")
    private boolean isNull = true;

    /**
     * 字段排序
     */
    @ApiModelProperty(value = "字段排序", name = "sort")
    private boolean sort = true;

    /**
     * 是否主键
     */
    @ApiModelProperty(value = "是否主键", name = "isKey")
    private boolean isKey = true;

    /**
     * 注释
     */
    @ApiModelProperty(value = "注释", name = "comments")
    private boolean comment = true;

    /**
     * jdbc 类型
     */
    @ApiModelProperty(value = "jdbc 类型", name = "jdbcType")
    private boolean jdbcType = true;

    /**
     * 表单类型
     */
    @ApiModelProperty(value = "表单 类型", name = "formType")
    private boolean formType = true;

    /**
     * 属性
     */
    @ApiModelProperty(value = "属性", name = "javaField")
    private boolean field = true;

    /**
     * 是否为插入字段（1：插入字段）
     */
    @ApiModelProperty(value = "是否插入", name = "isInsert")
    private boolean isInsert = true;

    /**
     * 是否编辑字段（1：编辑字段）
     */
    @ApiModelProperty(value = "是否修改", name = "isEdit")
    private boolean isEdit = true;

    /**
     * 是否列表字段（1：列表字段）
     */
    @ApiModelProperty(value = "是否列表展现", name = "isList")
    private boolean isList = true;

    /**
     * 是否查询字段（1：查询字段）
     */
    @ApiModelProperty(value = "是否查询字段", name = "isQuery")
    private boolean isQuery = true;

    /**
     * 查询类型
     */
    @ApiModelProperty(value = "查询类型", name = "queryType")
    private boolean queryType = true;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateDate")
    private boolean updateDate = true;

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isGenTable() {
        return genTable;
    }

    public void setGenTable(boolean genTable) {
        this.genTable = genTable;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean isNull) {
        this.isNull = isNull;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean isKey) {
        this.isKey = isKey;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(boolean jdbcType) {
        this.jdbcType = jdbcType;
    }

    public boolean isFormType() {
        return formType;
    }

    public void setFormType(boolean formType) {
        this.formType = formType;
    }

    public boolean isField() {
        return field;
    }

    public void setField(boolean field) {
        this.field = field;
    }

    public boolean isInsert() {
        return isInsert;
    }

    public void setInsert(boolean isInsert) {
        this.isInsert = isInsert;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public boolean isList() {
        return isList;
    }

    public void setList(boolean isList) {
        this.isList = isList;
    }

    public boolean isQuery() {
        return isQuery;
    }

    public void setQuery(boolean isQuery) {
        this.isQuery = isQuery;
    }

    public boolean isQueryType() {
        return queryType;
    }

    public void setQueryType(boolean queryType) {
        this.queryType = queryType;
    }

}