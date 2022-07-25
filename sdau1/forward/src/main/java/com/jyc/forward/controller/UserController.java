package com.jyc.forward.controller;

import com.jyc.forward.model.User;
import com.jyc.forward.service.UserService;
import com.jyc.forward.util.Constant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

@Controller
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
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user,String code, HttpSession session, Map<String,Object> map) {
        if(code==null || code.trim().equals("")) {
            map.put("detail","验证码为空！");
            return "login";
        }
        //验证码一分钟失效
        String code1 = "" + session.getAttribute("verification");
        if(!code1.equals(code)) {
            map.put("detail","验证码输入有误！");
            return "login";
        }

        //令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getMiPassword());
        //设置i
        usernamePasswordToken.setRememberMe(true);
        //认证
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            subject.getSession().setTimeout(1000*60*60);
            user.setLoginTime(Calendar.getInstance().getTime());
            user.setIsExam("否");
            int row = service.update(user);
            if(row>0) {
                map.put("detail","登陆成功！");
                session.setAttribute("login",user);
                return "redirect:/index";
            } else {
                map.put("detail","登陆成功！");
                return "login";
            }
        } catch (UnknownAccountException e) {
            map.put("detail","用户不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            map.put("detail","密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLogin";
    }

    @GetMapping("/register")
    public String toRegister() {
        return "register";
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
        return "redirect:/toLogin";
    }

    @GetMapping("/back")
    public String toBAck() {
        return "back";
    }

    @PostMapping("/back")
    public String back(User user,HttpSession session,Map<String,Object> map) {
        User user1 = service.findByName(user.getName());
        if(!user1.getPhone().equals(user.getPhone())) {
            map.put("detail","修改失败！");
            map.put("user",user);
            return "back";
        }
        int row = service.update(user);
        if(row<=0) {
            map.put("detail","修改失败！");
            map.put("user",user);
            return "back";
        }
        session.setAttribute("detail","修改成功！");
        return "redirect:/toLogin";
    }

    @RequestMapping("/my")
    public String my(User user, Map<String,Object> map, HttpSession session, @RequestParam(name = "picturex") MultipartFile multipartFile) {
        if(multipartFile.getOriginalFilename() != null&& multipartFile.getOriginalFilename().trim().length() > 0) {
            String uuid = UUID.randomUUID().toString();
            int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
            String name = uuid + "." + multipartFile.getOriginalFilename().substring(idx + 1);
            String url = Constant.PICTURE_URL +"user/" + name;
            File file = new File(url);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            user.setPicture("user/"+name);
        }
        int row =  service.update(user);
        if(row<=0) {
            map.put("detail","修改失败!");
            map.put("user",user);
            return "user/user";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/index";
        }
    }
}
