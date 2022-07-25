package com.jyc.background.config;

import com.jyc.background.model.Admin;
import com.jyc.background.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRolema extends AuthorizingRealm {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
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
        Admin admin = adminService.findByName(usernamePasswordToken.getUsername());
        if(admin==null) {
            return null;
        }
        return new SimpleAuthenticationInfo("",admin.getPassword(),"");
    }
}
