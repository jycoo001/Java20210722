package com.jyc.background.config;

import com.jyc.background.model.Master;
import com.jyc.background.service.MasterService;
import com.jyc.background.service.MasterService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MasterRolema extends AuthorizingRealm {

    private MasterService service;
    @Autowired
    public void setService(MasterService service) {
        this.service = service;
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
        Master master = service.findByName(usernamePasswordToken.getUsername());
        if(master==null) {
            return null;
        }
        return new SimpleAuthenticationInfo("",master.getPassword(),"");
    }
}
