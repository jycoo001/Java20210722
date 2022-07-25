package com.jyc.forward.controller;

import com.jyc.forward.dao.UserDAO;
import com.jyc.forward.dao.UserPracticeDAO;
import com.jyc.forward.model.User;
import com.jyc.forward.model.UserPractice;
import com.jyc.forward.service.UserPracticeService;
import com.jyc.forward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HisTestController {
    private UserPracticeService service;
    @Autowired
    public void setService(UserPracticeService service) {
        this.service = service;
    }
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hisTest")
    public String hisTest(HttpSession session, Map<String,Object> map,String uuid) {
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        map.put("user",user1);
        service.findByQuestion(uuid,map);
        return "hisT";
    }
}
