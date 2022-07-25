package com.jyc.forward.config;

import com.jyc.forward.model.User;
import com.jyc.forward.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRolema extends AuthorizingRealm {

    private UserService userService;

    @Autowired
    public void setAdminService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        User user = userService.findByName(usernamePasswordToken.getUsername());
        if(user==null) {
            return null;
        }
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
