package com.jyc.background.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = {"","/","/index"})
    public String index(Map<String,Object> map,HttpSession session) {
        map.put("name",session.getAttribute("name"));
        return "index";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name, String password, Map<String,Object> map, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);
        try {
            subject.login(usernamePasswordToken);

            session.setAttribute("name",name);
            return "redirect:/";
        } catch (Exception e) {
            map.put("detail","您输入的信息有误");
            return "redirect:/toLogin";
        }
    }



    @RequestMapping("/changepwd")
    public String changepwd() {
        return "changepwd";
    }

    @RequestMapping("/myloginfo")
    public String myLoginFo() {
        return "myloginfo";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/toLogin";
    }
}
