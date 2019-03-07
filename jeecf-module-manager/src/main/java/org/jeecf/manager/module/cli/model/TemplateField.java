package org.jeecf.manager.module.cli.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版参数实体
 * 
 * @author jianyiming
 * @version 2.0
 */
@ApiModel(value = "templateField", description = "模版参数实体")
public class TemplateField {
    /**
     * 参数名
     */
    @ApiModelProperty(value = "参数名", name = "key")
    private String key;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值", name = "value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
