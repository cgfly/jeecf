package org.jeecf.gen.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 代码表字段信息
 * 
 * @author jianyiming
 *
 */
public class BaseTableColumn {

    /**
     * 名称
     */
    private String name;

    /**
     * 是否空值
     */
    private int isNull;

    /**
     * 字段排序
     */
    private int sort;

    /**
     * 是否主键
     */
    private int isKey;

    /**
     * 注释
     */
    private String comment;

    /**
     * jdbc 类型
     */
    private String jdbcType;

    /**
     * 表单 类型
     */
    private int formType;
    /**
     * 属性
     */
    private String field;

    /**
     * 是否为插入字段（1：插入字段）
     */
    private int isInsert;

    /**
     * 是否编辑字段（1：编辑字段）
     */
    private int isEdit;

    /**
     * 是否列表字段（1：列表字段）
     */
    private int isList;

    /**
     * 是否查询字段（1：查询字段）
     */
    private int isQuery;

    /**
     * 查询类型
     */
    private int queryType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsNull() {
        return isNull;
    }

    public void setIsNull(int isNull) {
        this.isNull = isNull;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public int getIsKey() {
        return isKey;
    }

    public void setIsKey(int isKey) {
        this.isKey = isKey;
    }

    public int getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(int isInsert) {
        this.isInsert = isInsert;
    }

    public int getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(int isEdit) {
        this.isEdit = isEdit;
    }

    public int getIsList() {
        return isList;
    }

    public void setIsList(int isList) {
        this.isList = isList;
    }

    public int getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(int isQuery) {
        this.isQuery = isQuery;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getFormType() {
        return formType;
    }

    public void setFormType(int formType) {
        this.formType = formType;
    }

    /**
     * 获取jdbc简写类型
     * 
     * @return
     */
    public String getSimpleJdbcType() {
        return StringUtils.substringBeforeLast(getJdbcType(), "(");
    }

    /**
     * 获取jdbc整型长度
     * 
     * @return
     */
    public String getJdbcDataLength() {
        int minLength = 1;
        String[] ss = StringUtils.split(StringUtils.substringBetween(getJdbcType(), "(", ")"), ",");
        if (ss != null && ss.length >= minLength) {
            return ss[0];
        }
        return "0";
    }

    /**
     * 获取jdbc浮点型长度
     * 
     * @return
     */
    public String getJdbcDataFloatLength() {
        int minLength = 2;
        String[] ss = StringUtils.split(StringUtils.substringBetween(getJdbcType(), "(", ")"), ",");
        if (ss != null && ss.length >= minLength) {
            return ss[1];
        }
        return "0";
    }

}
