package org.jeecf.manager.module.config.model.schema;

import io.swagger.annotations.ApiModelProperty;

/**
 * 数据源schema
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SysUserDbsourceSchema {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private boolean id = true;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private boolean userId = true;
    /**
     * 命名空间id
     */
    @ApiModelProperty(value = "数据源id", name = "dbsourceId")
    private boolean dbsourceId = true;
    /**
     * 数据源名称
     */
    @ApiModelProperty(value = "数据源名称", name = "dbsourceName")
    private boolean dbsourceName = true;
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

    public boolean isUserId() {
        return userId;
    }

    public void setUserId(boolean userId) {
        this.userId = userId;
    }

    public boolean isDbsourceId() {
        return dbsourceId;
    }

    public void setDbsourceId(boolean dbsourceId) {
        this.dbsourceId = dbsourceId;
    }

    public boolean isUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(boolean updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDbsourceName() {
        return dbsourceName;
    }

    public void setDbsourceName(boolean dbsourceName) {
        this.dbsourceName = dbsourceName;
    }
    
}
