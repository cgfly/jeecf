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
    @ApiModelProperty(value = "模版名称", name = "template")
    private String template;
    /**
     * 表信息
     */
    @ApiModelProperty(value = "表信息", name = "table")
    private TableModel table;
    /**
     * 模版属性
     */
    @ApiModelProperty(value = "模版属性", name = "fields")
    private List<TemplateField> fields;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }

}
