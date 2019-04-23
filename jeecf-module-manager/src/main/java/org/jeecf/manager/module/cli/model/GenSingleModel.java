package org.jeecf.manager.module.cli.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 代码生成 单例
 * 
 * @author jianyiming
 *
 */
@ApiModel(value = "genSingleModel", description = "代码生成单例实体")
public class GenSingleModel {
    /**
     * 模版名称
     */
    @ApiModelProperty(value = "模版名称", name = "templateName")
    private String templateName;
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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

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
