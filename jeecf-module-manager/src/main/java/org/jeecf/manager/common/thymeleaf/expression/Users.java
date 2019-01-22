package org.jeecf.manager.common.thymeleaf.expression;

import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.userpower.model.domain.SysUser;

/**
 * users表达式
 * 
 * @author jianyiming
 *
 */
public final class Users {

    /**
     * 获取用户名
     * 
     * @return
     */
    public String getName() {
        SysUser sysUser = UserUtils.getCurrentUser();
        return sysUser.getUsername();
    }

}
