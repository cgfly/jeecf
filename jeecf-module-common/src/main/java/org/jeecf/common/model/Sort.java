package org.jeecf.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 排序实体
 * 
 * @author jianyiming
 *
 */
@ApiModel(value = "sort", description = "排序实体")
public class Sort {
    /**
     * 字段名称
     */
    @ApiModelProperty(value = "字段名称", name = "columnName")
    private String columnName;

    @ApiModelProperty(value = "排序方式（ASC、DESC）", name = "sortMode")
    private String sortMode;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

}
