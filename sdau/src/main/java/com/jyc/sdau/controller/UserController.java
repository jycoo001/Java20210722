package com.jyc.sdau.controller;

import com.jyc.sdau.model.User;
import com.jyc.sdau.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/forward")
public class UserController {

    private UserService service;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }


    @GetMapping("/toLogin")
    public String toLogin(HttpSession session,Map<String,Object> map) {
        Object obj = session.getAttribute("detail");
        session.removeAttribute("detail");
        map.put("detail",obj);
        return "forward/login";
    }

    @RequestMapping("/login")
    public String login(User user,String code, HttpSession session, Map<String,Object> map) {
        if(code==null || code.trim().equals("")) {
            map.put("detail","验证码为空！");
            return "forward/login";
        }
        //验证码一分钟失效
        String code1 = "" + session.getAttribute("verification");
        if(!code1.equals(code)) {
            map.put("detail","验证码输入有误！");
            return "forward/login";
        }

        //令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getMiPassword());
        //设置i
        usernamePasswordToken.setRememberMe(true);
        //认证
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            user.setLoginTime(Calendar.getInstance().getTime());
            user.setIsExam("0");
            int row = service.update(user);
            if(row>0) {
                map.put("detail","登陆失败！");
                return "forward/login";
            } else {
                map.put("detail","登陆成功！");
                return "redirect:/background/login";
            }
        } catch (UnknownAccountException e) {
            map.put("detail","用户不存在！");
            return "forward/login";
        } catch (IncorrectCredentialsException e) {
            map.put("detail","密码错误");
            return "forward/login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/forward/toLogin";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "forward/register";
    }

    @PostMapping("/register")
    public String register(User user,HttpSession session,Map<String,Object> map) {
        user.setStatus("1");
        int rows = service.register(user);
        if(rows<=0) {
            map.put("detail","注册失败！");
            System.out.println("注册失败！");
        }
        session.setAttribute("detail","注册成功！");
        return "redirect:/forward/toLogin";
    }

    @GetMapping("/back")
    public String toBAck() {
        return "forward/back";
    }

    @PostMapping("/back")
    public String back(User user,HttpSession session,Map<String,Object> map) {
        User user1 = service.findByName(user.getName());
        if(!user1.getPhone().equals(user.getPhone())) {
            map.put("detail","修改失败！");
            map.put("user",user);
            return "forward/back";
        }
        int row = service.update(user);
        if(row<=0) {
            map.put("detail","修改失败！");
            map.put("user",user);
            return "forward/back";
        }
        session.setAttribute("detail","修改成功！");
        return "redirect:/forward/toLogin";
    }
}
