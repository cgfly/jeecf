package org.jeecf.manager.module.cli.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版代码输入实体
 * 
 * @author jianyiming
 * @version 2.0
 */
@ApiModel(value = "templateCodeInput", description = "模版代码输入实体")
public class TemplateCodeInput extends AuthModel {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    /**
     * 命名空间
     */
    @ApiModelProperty(value = "命名空间", name = "namespace")
    private String namespace;
    /**
     * 表名
     */
    @ApiModelProperty(value = "表名", name = "tableName")
    private String tableName;

    /**
     * 模版参数
     */
    @ApiModelProperty(value = "模版参数", name = "templateFields")
    private List<TemplateField> templateFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TemplateField> getTemplateFields() {
        return templateFields;
    }

    public void setTemplateFields(List<TemplateField> templateFields) {
        this.templateFields = templateFields;
    }

}
