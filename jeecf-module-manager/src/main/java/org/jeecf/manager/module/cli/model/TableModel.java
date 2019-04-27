package org.jeecf.manager.module.cli.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表实体
 * 
 * @author jianyiming
 * 
 */
@ApiModel(value = "tableModel", description = "表实体")
public class TableModel {

    @ApiModelProperty(value = "表名", name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
