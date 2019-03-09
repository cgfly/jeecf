package org.jeecf.manager.common.utils;

import org.apache.shiro.SecurityUtils;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.service.SysUserService;

/**
 * 用户工具类
 * 
 * @author jianyiming
 *
 */
public class UserUtils {

    private static final SysUserService SYS_USER_SERVICE = SpringContextUtils.getBean("sysUserService", SysUserService.class);

    /**
     * 获取当前用户标示
     * 
     * @return
     */
    public static String getCurrentUserId() {
        String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
        return (String) RedisCacheUtils.getSysCache(sessionId);
    }

    public static String getUserId(String sessionId) {
        return (String) RedisCacheUtils.getSysCache(sessionId);
    }

    /**
     * 获取当前用户
     * 
     * @return
     */
    public static SysUser getCurrentUser() {
        String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
        String id = (String) RedisCacheUtils.getSysCache(sessionId);
        SysUser queryUser = new SysUser();
        queryUser.setId(id);
        return SYS_USER_SERVICE.get(queryUser).getData();
    }

}
