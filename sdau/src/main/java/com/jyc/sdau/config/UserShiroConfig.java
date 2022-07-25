package com.jyc.sdau.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class UserShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurity") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filters = new LinkedHashMap<>();
        /*
            anon:无需认证就可以访问
            authc：必须认证才能访问
            user：必记住我 功能才能访问须拥有
            perms：拥有对某个资源的权限
            role：拥有对某个角色的权限
         */
        //filters.put("/user/add","anno");
        //
        filters.put("/static/**","anon");
        filters.put("/forward/login","anon");
        filters.put("/forward/register","anon");
        filters.put("/forward/back","anon");
        filters.put("/forward/**","authc");
//        filters.put("*.css","anon");
//        filters.put("*.js","anon");
//        filters.put("*.png","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        shiroFilterFactoryBean.setLoginUrl("/forward/toLogin");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurity(@Qualifier("userRolema") UserRolema userRolema) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRolema);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRolema userRolema() {
        return new UserRolema();
    }



    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBeanMaster(@Qualifier("getDefaultWebSecurityMaster") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filters = new LinkedHashMap<>();
        /*
            anon:无需认证就可以访问
            authc：必须认证才能访问
            user：必记住我 功能才能访问须拥有
            perms：拥有对某个资源的权限
            role：拥有对某个角色的权限
         */
        //filters.put("/user/add","anno");
        //
        filters.put("/static/**","anon");
        filters.put("/background/login","anon");
        filters.put("/background/**","authc");
//        filters.put("*.css","anon");
//        filters.put("*.js","anon");
//        filters.put("*.png","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        shiroFilterFactoryBean.setLoginUrl("/background/toLogin");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityMaster(@Qualifier("masterRolema") MasterRolema masterRolema) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(masterRolema);
        return defaultWebSecurityManager;
    }

    @Bean
    public MasterRolema masterRolema() {
        return new MasterRolema();
    }




}
