package com.jyc.sdau.controller;

import com.jyc.sdau.model.Master;
import com.jyc.sdau.model.User;
import com.jyc.sdau.service.MasterService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/background")
public class MasterController {

    private MasterService service;
    @Autowired
    public void setService(MasterService service) {
        this.service = service;
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "background/login";
    }

    @RequestMapping("/login")
    public String login(Master master, String code, HttpSession session, Map<String,Object> map) {
        if(code==null || code.trim().equals("")) {
            map.put("detail","验证码为空！");
            return "background/login";
        }
        //验证码一分钟失效
        String code1 = "" + session.getAttribute("verification");
        if(!code1.equals(code)) {
            map.put("detail","验证码输入有误！");
            return "background/login";
        }
        //令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(master.getName(),master.getMiPassword());
        //设置i
        usernamePasswordToken.setRememberMe(true);
        //认证
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            master.setLoginTime(Calendar.getInstance().getTime());
            int row = service.update(master);
            if(row>0) {
                map.put("detail","登陆成功！");
                return "redirect:/background/index";
            } else {
                map.put("detail","登陆失败！");
                System.out.println("登陆失败！");
                return "background/login";
            }
        } catch (UnknownAccountException e) {
            map.put("detail","用户不存在！");
            return "background/login";
        } catch (IncorrectCredentialsException e) {
            map.put("detail","密码错误");
            return "background/login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/background/toLogin";
    }

}
