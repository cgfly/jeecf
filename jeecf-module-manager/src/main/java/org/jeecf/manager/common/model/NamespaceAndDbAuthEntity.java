package org.jeecf.manager.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 命名空间数据源验证实体
 * 
 * @author jianyiming
 *
 */
public class NamespaceAndDbAuthEntity extends NamespaceAuthEntity {

    public NamespaceAndDbAuthEntity() {
    }

    public NamespaceAndDbAuthEntity(String id) {
        super(id);
    }

    @ApiModelProperty(value = "数据源", name = "sysDbsouceId")
    private Integer sysDbsourceId;

    public Integer getSysDbsourceId() {
        return sysDbsourceId;
    }

    public void setSysDbsourceId(Integer sysDbsourceId) {
        this.sysDbsourceId = sysDbsourceId;
    }

}
