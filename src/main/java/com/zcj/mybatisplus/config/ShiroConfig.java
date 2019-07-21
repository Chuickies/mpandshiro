package com.zcj.mybatisplus.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置拦截
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout","anon" );
        filterChainDefinitionMap.put("/afterlogout","anon" );
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/user/**","anon" );
        filterChainDefinitionMap.put("/test/**","anon" );
        filterChainDefinitionMap.put("/page/**","anon" );
        // 如果不设置会自动找到WEB 工程目录下的"/login.html" 页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/failure.html");  //未授权时的跳转
        //跳转成功后的页面
        shiroFilterFactoryBean.setSuccessUrl("/page/main.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

}
