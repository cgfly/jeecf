package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.validate.constraints.English;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统角色
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "名称不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.enname)", message = "英文名称不能为空", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.sysPowerIds)", message = "权限输入不能为空", groups = { Add.class }) 
})
@ApiModel(value = "sysRole", description = "系统角色实体")
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 中文名
     */
    @ApiModelProperty(value = "中文名", name = "name")
    private String name;

    /**
     * 英文名
     */
    @ApiModelProperty(value = "英文名", name = "enname")
    private String enname;
    /**
     * 权限集合
     */
    @ApiModelProperty(value = "权限集合", name = "sysPowerIds")
    private List<String> sysPowerIds;

    public SysRole() {
        super();
    }

    public SysRole(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "中文名长度必须介于 1 和 20 之间", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 20, message = "英文名长度必须介于 1 和 20 之间", groups = { Add.class })
    @English(message = "英文名只能为英文字符", groups = { Add.class })
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    @Size(min = 1, max = 100, message = "超过范围，最大可添加100个参数", groups = { Add.class })
    public List<String> getSysPowerIds() {
        return sysPowerIds;
    }

    public void setSysPowerIds(List<String> sysPowerIds) {
        this.sysPowerIds = sysPowerIds;
    }

}