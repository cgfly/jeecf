package org.jeecf.manager.module.operation.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.operation.model.domain.CustomerActionLog;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户操作日志 结果
 * 
 * @author jianyiming
 *
 */
public class CustomerActionLogResult extends CustomerActionLog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作类型名称
     */
    @ApiModelProperty(value = "操作类型名称", name = "actionTypeName")
    private String actionTypeName;

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

}
