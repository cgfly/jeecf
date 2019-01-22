package org.jeecf.manager.module.extend.model.result;

import java.io.Serializable;

import org.jeecf.common.enums.IfTypeEnum;
import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTableColumn;

import io.swagger.annotations.ApiModelProperty;

/**
 * 虚表结果返回实体
 * 
 * @author jianyiming
 *
 */
public class SysVirtualTableColumnResult extends SysVirtualTableColumn implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", name = "typeName")
    private String typeName;
    /**
     * 为空名称
     */
    @ApiModelProperty(value = "是否为空名称", name = "isNotNullName")
    private String isNotNullName;

    /**
     * 是否主键名称
     */
    @ApiModelProperty(value = "是否主键名称", name = "isKeyName")
    private String isKeyName;
    /**
     * 是否自增名称
     */
    @ApiModelProperty(value = "是否自增名称", name = "isAutoName")
    private String isAutoName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIsNotNullName() {
        return isNotNullName;
    }

    public void setIsNotNullName(String isNotNullName) {
        this.isNotNullName = isNotNullName;
    }

    public String getIsKeyName() {
        return isKeyName;
    }

    public void setIsKeyName(String isKeyName) {
        this.isKeyName = isKeyName;
    }

    public String getIsAutoName() {
        return isAutoName;
    }

    public void setIsAutoName(String isAutoName) {
        this.isAutoName = isAutoName;
    }

    public void toCovert() {
        if (this.getType() != null) {
            this.setTypeName(TableTypeEnum.getName(this.getType()));
        }
        if (this.getIsNotNull() != null) {
            this.setIsNotNullName(IfTypeEnum.getName(this.getIsNotNull()));
        }
        if (this.getIsKey() != null) {
            this.setIsKeyName(IfTypeEnum.getName(this.getIsKey()));
        }
        if (this.getIsAuto() != null) {
            this.setIsAutoName(IfTypeEnum.getName(this.getIsAuto()));
        }
    }

}
