
package com.nike.app.dq.boot.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nike.app.dq.boot.security.SimpleRealm;

import javax.servlet.Filter;

@Configuration
public class ShiroConfig {

	@Bean
	public SimpleRealm simpleRealm() {
		SimpleRealm simpleRealm = new SimpleRealm();
		return simpleRealm;
	}

    @Bean
    public DefaultWebSecurityManager securityManager() {
    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(simpleRealm());
        return securityManager;
    }

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/user/default/loginform");
		shiroFilterFactoryBean.setSuccessUrl("/dashboard/user/home");
		shiroFilterFactoryBean.setUnauthorizedUrl("/user/default/logout");
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("authc", new PassThruAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(filters);
		Map<String, String> filterChainDefinitions = new HashMap<String, String>();
		filterChainDefinitions.put("/user/default/loginform", "anon");
//		filterChainDefinitions.put("/user/default/image/captcha", "anon");
		filterChainDefinitions.put("/resources/**", "anon");
		filterChainDefinitions.put("/index.jsp", "anon");
		filterChainDefinitions.put("/rule/api/**", "anon");
		filterChainDefinitions.put("/v2/api-docs", "anon");
		filterChainDefinitions.put("/rule/tc/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/rule/qs/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/rule/r21fc/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/rule/r33mc/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/schedule/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/ds/list/form", "perms[suser:view]");
		filterChainDefinitions.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
		return shiroFilterFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}
}