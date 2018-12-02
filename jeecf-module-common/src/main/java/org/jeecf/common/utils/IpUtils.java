package org.jeecf.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
/**
 * ip 工具类
 * @author jianyiming
 *
 */
public class IpUtils {
	/**
	 * 不知到的地址
	 */
	private static final String UNKNOWN = "unKnown";
    /**
     * 获取客户端ip
     * @param request
     * @return
     */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(SplitCharEnum.COMMA.getName());
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

}
