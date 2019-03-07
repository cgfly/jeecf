package org.jeecf.manager.module.config.model.domain;

import java.io.Serializable;

import org.jeecf.manager.common.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户数据源对应实体
 * 
 * @author jianyiming
 * @version 2.0
 */
@ApiModel(value = "sysUserDbsource", description = "用户数据源对应实体")
public class SysUserDbsource extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private String userId;
    /**
     * 命名空间id
     */
    @ApiModelProperty(value = "数据源id", name = "dbsourceId")
    private Integer dbsourceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDbsourceId() {
        return dbsourceId;
    }

    public void setDbsourceId(Integer dbsourceId) {
        this.dbsourceId = dbsourceId;
    }

}
