package com.zcj.mybatisplus.config;


import com.zcj.mybatisplus.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置拦截
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出 具体的代码shiro已经实现
        filterChainDefinitionMap.put("/logout","anon" );
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/css/**","anon" );
        filterChainDefinitionMap.put("/js/**","anon" );
        filterChainDefinitionMap.put("/images/**","anon" );
        filterChainDefinitionMap.put("/page/**","authc" );
        // 如果不设置会自动找到WEB 工程目录下的"/login.jsp" 页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/failure.html");  //未授权时的跳转
        //登录成功默认跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/page/main.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserRealm());
        return securityManager;
    }

    @Bean
    public Realm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

}
