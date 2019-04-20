package org.jeecf.manager.module.cli.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代码生成 单例
 * 
 * @author jianyiming
 *
 */
public class GenSingleModel {
    /**
     * 表名
     */
    @ApiModelProperty(value = "表名", name = "tableName")
    private String tableName;
    /**
     * 模版属性
     */
    @ApiModelProperty(value = "模版属性", name = "fields")
    private List<TemplateField> fields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }

}
