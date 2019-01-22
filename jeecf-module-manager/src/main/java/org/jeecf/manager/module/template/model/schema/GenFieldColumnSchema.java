package org.jeecf.manager.module.template.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代码属性参数列表 schema
 * 
 * @author jianyiming
 *
 */
public class GenFieldColumnSchema {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private boolean id = true;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private boolean name = true;
    /**
     * 允许为空
     */
    @ApiModelProperty(value = "为空", name = "isNull")
    private boolean isNull = true;
    /**
     * 默认值
     */
    @ApiModelProperty(value = "为空", name = "isNull")
    private boolean defaultValue = true;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private boolean description = true;
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

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

}
