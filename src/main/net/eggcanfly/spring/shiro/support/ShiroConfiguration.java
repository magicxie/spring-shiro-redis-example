package net.eggcanfly.spring.shiro.support;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

		SecurityManager securityManager = new DefaultWebSecurityManager();
		
		return securityManager;
	}
}
