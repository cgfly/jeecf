package org.jeecf.manager.validate.constraints;

import org.apache.commons.lang3.StringUtils;

/**
 * 脚本验证
 * 
 * @author jianyiming
 *
 */
public class Script {

    public static boolean notNull(String id, Object obj) {
        if (StringUtils.isEmpty(id) && obj == null) {
            return false;
        }
        return true;
    }

    public static boolean notBlank(String id, String obj) {
        if (StringUtils.isEmpty(id) && StringUtils.isBlank(obj)) {
            return false;
        }
        return true;
    }

}
