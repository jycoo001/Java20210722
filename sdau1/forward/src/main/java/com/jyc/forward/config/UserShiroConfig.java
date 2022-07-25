package com.jyc.forward.config;

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
        filters.put("/login","anon");
        filters.put("/register","anon");
        filters.put("/back","anon");
        filters.put("/code","anon");
        filters.put("/**","authc");
//        filters.put("*.css","anon");
//        filters.put("*.js","anon");
//        filters.put("*.png","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
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


}
