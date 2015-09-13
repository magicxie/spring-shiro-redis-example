package net.eggcanfly.spring.shiro.support;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

	@Bean
	public FactoryBean<Filter> shiroFilter(){
		
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		filter.setSecurityManager(securityManager());
		
		return filter;
	}

	@Bean
	public SecurityManager securityManager() {

		SessionsSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(sessionManager());
		
		return securityManager;
	}

	protected DefaultSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		
		sessionManager.setSessionDAO(sessionDao() );
		return sessionManager;
	}

	protected SessionDAO sessionDao() {
		SessionDAO sessionDAO = redisSessionDAO();
		return sessionDAO;
	}

	@Bean
	public RedisSessionDao redisSessionDAO() {
		return new RedisSessionDao();
	}
}
