package org.jeecf.manager.config;

import org.jeecf.manager.listen.UserLoginListener;
import org.jeecf.manager.listen.UserLogoutListener;
import org.jeecf.manager.subject.UserSubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 监听者 注入
 * @author jianyiming
 *
 */
@Configuration
public class SubjectConfiguration {
	
	@Bean
	public UserSubject userSubject() {
		UserSubject subject = new UserSubject(); 
		subject.register(UserSubject.TOPIC_ELEMENTS[0], new UserLoginListener());
		subject.register(UserSubject.TOPIC_ELEMENTS[1], new UserLogoutListener());
		return subject;
	}

}
