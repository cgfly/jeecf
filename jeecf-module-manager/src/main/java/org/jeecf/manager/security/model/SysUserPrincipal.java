package org.jeecf.manager.security.model;

import java.io.Serializable;

/**
 * 系统用户 Principal实体
 * 
 * @author jianyiming
 *
 */
public class SysUserPrincipal implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
