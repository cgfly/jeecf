package org.jeecf.manager.module.config.model.domain;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.PermissionEntity;
import org.jeecf.manager.validate.constraints.Jdbc;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.keyName)", message = "{dbsource.keyName.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.url)", message = "{dbsource.url.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.userName)", message = "{dbsource.userName.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.password)", message = "{dbsource.password.isEmpty}", groups = { Add.class }) 
})
@ApiModel(value = "sysDbsource", description = "系统数据源实体")
public class SysDbsource extends PermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", name = "keyName")
    private String keyName;
    /**
     * 连接串
     */
    @ApiModelProperty(value = "连接串", name = "url")
    private String url;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    /**
     * 连接是否可用
     */
    @ApiModelProperty(value = "连接是否可用", name = "usable")
    private Integer usable;

    public SysDbsource() {
        super();
    }

    public SysDbsource(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "{dbsource.keyName.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z_-.]+$", message = "{dbsource.keyName.pattern}", groups = { Add.class })
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Length(min = 1, max = 150, message = "{dbsource.url.length}", groups = { Add.class })
    @Jdbc(message = "{dbsource.url.jdbc}", groups = { Add.class })
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Length(min = 1, max = 20, message = "{dbsource.userName.length}", groups = { Add.class })
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Length(min = 1, max = 64, message = "{dbsource.password.length}", groups = { Add.class })
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsable() {
        return usable;
    }

    public void setUsable(Integer usable) {
        this.usable = usable;
    }

}