//package com.robot.study.common.shrio;
//
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author Wuph
// * @Date: create in 2021/11/10/ 17:55
// * @Description
// */
//@Configuration
//public class ShiroConfiguration {
//
//    //1.创建shiroFilter  //负责拦截所有请求
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        //给filter设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//
//        //配置系统受限资源
//        //配置系统公共资源
//        Map<String,String> map = new HashMap<String,String>();
//        //auth 请求这个资源需要认证和授权
//        map.put("/index.jsp","auth");
//
//        //默认认证界面路径
//        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//
//        return shiroFilterFactoryBean;
//    }
//
//    //2.创建安全管理器
//    @Bean
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        //给安全管理器设置
//        defaultWebSecurityManager.setRealm(realm);
//
//        return defaultWebSecurityManager;
//    }
//
//    //3.创建自定义realm
//    @Bean
//    public Realm getRealm(){
//        return new CustomerRealm();
//    }
//
//}
