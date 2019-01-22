package org.jeecf.common.utils;

import org.jeecf.common.model.Response;

/**
 * 响应实体验证
 * 
 * @author jianyiming
 *
 */
public class ResponseUtils {

    /**
     * 不为空判断
     * 
     * @param response
     * @return
     */
    public static <T> boolean isNotEmpty(Response<T> response) {
        return response.isSuccess() && response.getData() != null;
    }

    /**
     * 为空判断
     * 
     * @param response
     * @return
     */
    public static <T> boolean isEmpty(Response<T> response) {
        return !ResponseUtils.isNotEmpty(response);
    }
}
