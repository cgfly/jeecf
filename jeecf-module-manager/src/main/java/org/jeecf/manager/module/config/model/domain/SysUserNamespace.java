package org.jeecf.manager.module.config.model.domain;

import java.io.Serializable;

import org.jeecf.manager.common.model.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户命名空间对应
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value = "sysUserNamespace", description = "用户命名空间对应实体")
public class SysUserNamespace extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private String userId;
    /**
     * 命名空间id
     */
    @ApiModelProperty(value = "命名空间id", name = "namespaceId")
    private Integer namespaceId;

    public SysUserNamespace() {
        super();
    }

    public SysUserNamespace(String id) {
        super(id);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(Integer namespaceId) {
        this.namespaceId = namespaceId;
    }
}