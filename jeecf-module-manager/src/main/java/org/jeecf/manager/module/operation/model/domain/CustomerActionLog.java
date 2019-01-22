package org.jeecf.manager.module.operation.model.domain;

import java.io.Serializable;

import org.jeecf.manager.common.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户操作日志
 * 
 * @author jianyiming
 *
 */
@ApiModel(value = "customerActionLog", description = "客户操作日志")
public class CustomerActionLog extends BaseEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主机
     */
    @ApiModelProperty(value = "主机", name = "host")
    private String ip;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", name = "userName")
    private String userName;
    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型", name = "actionType")
    private Integer actionType;
    /**
     * 操作数据
     */
    @ApiModelProperty(value = "操作数据", name = "actionData")
    private String actionData;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
    }

}
