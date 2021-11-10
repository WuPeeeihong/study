//package com.robot.study.common.shrio;
//
//import com.robot.study.service.IUserService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @Author Wuph
// * @Date: create in 2021/11/10/ 17:54
// * @Description 自定义 realm
// */
//public class CustomerRealm extends AuthorizingRealm {
//
//    @Autowired
//    private IUserService userService;
//
//    // 授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        return null;
//    }
//
//    // 认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        return null;
//    }
//
//}
