package org.jeecf.manager.module.cli.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户验证实体
 * 
 * @author jianyiming
 * @version 2.0
 */
@ApiModel(value = "authModel", description = "用户验证实体")
public class AuthModel {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "username")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
