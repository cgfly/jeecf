package org.jeecf.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecf.common.utils.IpUtils;
import org.jeecf.manager.common.properties.ThreadLocalProperties;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * webApp /** 拦截器
 * 
 * @author jianyiming
 *
 */
@Slf4j
public class WebAppInterceptor implements HandlerInterceptor {

	private static ThreadLocalProperties threadLocalProperties = SpringContextUtils.getBean("threadLocalProperties",
			ThreadLocalProperties.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = IpUtils.getIp(request);
		threadLocalProperties.set("ip", ip);
		log.info("WebAppInterceptor: ip={}",ip);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		threadLocalProperties.remove();

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		threadLocalProperties.remove();
	}

}
