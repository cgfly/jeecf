package org.jeecf.manager.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.jeecf.manager.cache.RedisCacheManagerZ;
import org.jeecf.manager.cache.RedisSessionDAO;
import org.jeecf.manager.security.realm.SysShiroRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
/**
 * shiro 配置
 * @author jianyiming
 *
 */
@Configuration
@DependsOn(value= {"redisConfig","redisSessionDAO"})
public class ShiroConfiguration {
	
    @Resource
    private RedisSessionDAO sessionDAO;
    @Resource
    private RedisCacheManagerZ redisCacheManagerZ;
	   /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    	
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        //filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// anon：它对应的过滤器里面是空的,什么都没做
		filterChainDefinitionMap.put("/login", "anon");
	    filterChainDefinitionMap.put("/css/**", "anon");
	    filterChainDefinitionMap.put("/js/**", "anon");
	    filterChainDefinitionMap.put("/libs/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "perms[operation:swagger2:view]");
		filterChainDefinitionMap.put("/swagger-resources", "perms[operation:swagger2:view]");
		filterChainDefinitionMap.put("/v2/api-docs", "perms[operation:swagger2:view]");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "perms[operation:swagger2:view]");
		filterChainDefinitionMap.put("/druid/**","perms[operation:druid:view]");
		filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(SysShiroRealm sysShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(sysShiroRealm);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManagerZ);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        
        
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    
    @Bean
    public SysShiroRealm sysShiroRealm(){
    	SysShiroRealm shiroRealm = new SysShiroRealm();
    	shiroRealm.setCacheManager(redisCacheManagerZ);
        shiroRealm.setCachingEnabled(true);
        return shiroRealm;
    }

    /**
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(2592000);
       return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
       cookieRememberMeManager.setCookie(rememberMeCookie());
       //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
       cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
       return cookieRememberMeManager;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
            = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
  
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setCacheManager(redisCacheManagerZ);
        return sessionManager;
    }
    


   
   
}
