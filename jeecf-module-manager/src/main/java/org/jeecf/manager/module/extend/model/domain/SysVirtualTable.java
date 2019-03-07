package org.jeecf.manager.module.extend.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAndDbAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModelProperty;

/**
 * 虚表
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{virtualTable.name.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.comment)", message = "{virtualTable.comment.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.sysVirtualTableColumns)", message = "{virtualTable.tableColumns.isEmpty}", groups = { Add.class }) 
})
public class SysVirtualTable extends NamespaceAndDbAuthEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SysVirtualTable() {
    }

    public SysVirtualTable(String id) {
        super(id);
    }

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名", name = "name")
    private String name;
    /**
     * 注释
     */
    @ApiModelProperty(value = "注释", name = "comment")
    private String comment;
    /**
     * 表字段
     */
    @ApiModelProperty(value = "表字段", name = "sysVirtualTableColumns")
    private List<SysVirtualTableColumn> sysVirtualTableColumns;

    @Length(min = 1, max = 20, message = "{virtualTable.name.length}", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 50, message = "{virtualTable.comment.length}", groups = { Add.class })
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Valid
    @Size(min = 1, max = 30, message = "{virtualTable.tableColumns.size}", groups = { Add.class })
    public List<SysVirtualTableColumn> getSysVirtualTableColumns() {
        return sysVirtualTableColumns;
    }

    public void setSysVirtualTableColumns(List<SysVirtualTableColumn> sysVirtualTableColumns) {
        this.sysVirtualTableColumns = sysVirtualTableColumns;
    }

}
