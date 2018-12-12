package org.jeecf.manager.test.helper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
/**
 * 基础mock 用于实体类实现
 * @author jianyiming
 *
 */
public class BaseMokMvc {
	
	@Autowired
	protected WebApplicationContext context;

	@Autowired
	protected SecurityManager securityManager;
	
	protected Subject subject;
	protected MockMvc mockMvc;
	protected MockHttpServletRequest mockHttpServletRequest;
	protected MockHttpServletResponse mockHttpServletResponse;
	
	protected void init() {
		this.init(null,null);
	}
	
	protected void init(String username,String passwoed) {
		mockHttpServletRequest = new MockHttpServletRequest(context.getServletContext());
		mockHttpServletResponse = new MockHttpServletResponse();
		MockHttpSession mockHttpSession = new MockHttpSession(context.getServletContext());
		mockHttpServletRequest.setSession(mockHttpSession);
		SecurityUtils.setSecurityManager(securityManager);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		if(StringUtils.isNotEmpty(username)) {
			login(username,passwoed);
		}
	}

	protected void login(String username, String password) {
		subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse).buildWebSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
		subject.login(token);
		String id = (String) subject.getPrincipal();
		RedisCacheUtils.setSysCache((String)subject.getSession().getId(), id);
		ThreadContext.bind(subject);
	}

}
