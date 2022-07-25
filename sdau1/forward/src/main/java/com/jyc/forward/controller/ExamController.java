package com.jyc.forward.controller;

import com.jyc.forward.dao.UserDAO;
import com.jyc.forward.model.Exam;
import com.jyc.forward.model.User;
import com.jyc.forward.model.UserExam;
import com.jyc.forward.service.ExamService;
import com.jyc.forward.service.UserService;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ExamController {
    private ExamService service;
    @Autowired
    public void setService(ExamService service) {
        this.service =service;
    }


    private UserService userService;
    @Autowired
    public void setUserDAO(UserService userService) {
        this.userService =userService;
    }

    @RequestMapping("/toExam")
    public String toExam(HttpSession session, Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        map.put("user",user1);
        List<UserExam> list = service.find(user1.getId());
        map.put("list",list);
        return "selExam";
    }

    @RequestMapping("/toDoingExam")
    public String toDoingExam(String uuid, Map<String, Object> map,HttpSession session) {
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        user1.setIsExam("是");
        user1.setPassword("");
        userService.update(user1);
        map.put("user",user1);
        service.toDoing(uuid,map,session);
        return "examDoing";
    }

    @RequestMapping("/exam/sub")
    public String sub(String single, String multi, String pan, HttpSession session) {
        List<Integer> listSin = (List<Integer>) session.getAttribute("singleId");
        List<Integer> listMu = (List<Integer>) session.getAttribute("multiId");
        List<Integer> listPa = (List<Integer>) session.getAttribute("panId");
        User user = (User) session.getAttribute("login");
        String uuid = (String) session.getAttribute("uuid");
        User user1 = userService.findByName(user.getName());
        int row = service.save(single,multi,pan,listSin,listMu,listPa,user1.getId(),uuid);
        if(row<=0) {
            session.setAttribute("detail","交卷失败");
            return "redirect:/index";
        } else {
            user1.setIsExam("否");
            user1.setPicture("");
            userService.update(user1);
            session.setAttribute("detail","交卷成功");
            return "redirect:/index";
        }
    }

    @RequestMapping("/toHisExam")
    public String toHisExam(HttpSession session, Map<String,Object> map) {
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        map.put("user",user1);
        List<UserExam> list = service.findHis(user1.getId());
        map.put("list",list);
        return "hisExam";
    }

    @RequestMapping("/toExamLook")
    public String toExamLook(HttpSession session,Map<String,Object> map,String uuid) {
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        map.put("user",user1);
        service.findHisExam(uuid,map);
        return "hisT";
    }


}
