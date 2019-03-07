package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.OfficeAuthEntity;
import org.jeecf.manager.validate.constraints.English;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)", message = "{user.name.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.username)", message = "{user.username.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.password)", message = "{user.password.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.sysRoleIds)", message = "{user.roleIds.isNull}", groups = { Add.class }) 
})
@ApiModel(value = "sysUser", description = "系统用户实体")
public class SysUser extends OfficeAuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 账户
     */
    @ApiModelProperty(value = "账户", name = "username")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name")
    private String name;

    /**
     * 角色集合
     */
    @ApiModelProperty(value = "角色集合", name = "sysRoleIds")
    private List<String> sysRoleIds;

    public SysUser() {
        super();
    }

    public SysUser(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "{user.username.length}", groups = { Add.class })
    @English(message = "{user.username.english}", groups = { Add.class })
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 1, max = 64, message = "{user.password.length}", groups = { Add.class })
    @English(message = "{user.password.english}", groups = { Add.class })
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 1, max = 20, message = "{user.name.length}", groups = { Add.class })
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 1, max = 5, message = "{user.roleIds.size}超过范围，最大可添加5个参数", groups = { Add.class })
    public List<String> getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(List<String> sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }

}