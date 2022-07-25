package com.jyc.forward.controller;

import com.jyc.forward.model.User;
import com.jyc.forward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.nio.channels.Pipe;
import java.util.Map;

@Controller
public class IndexController {

    private UserService service;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping("/index")
    public String index (HttpSession session,Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = service.findByName(user.getName());
        map.put("user",user1);
        return "index";
    }

    @RequestMapping("/toMy")
    public String yo(HttpSession session, Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = service.findByName(user.getName());
        map.put("user",user1);
        return "user/user";
    }

    @RequestMapping("/toTest")
    public String to(HttpSession session,Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = service.findByName(user.getName());
        map.put("user",user1);
        return "test";
    }

    @RequestMapping("/toHisTest")
    public String tohis(HttpSession session,Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = service.findByName(user.getName());
        map.put("user",user1);
        return "hisTest";
    }

}
