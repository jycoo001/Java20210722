package com.jyc.forward.controller;

import com.jyc.forward.model.Pan;
import com.jyc.forward.model.Question;
import com.jyc.forward.model.User;
import com.jyc.forward.service.TestService;
import com.jyc.forward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class TestController {
    private TestService service;
    @Autowired
    public void setService(TestService service) {
        this.service=service;
    }

    private UserService userService;
    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/sel")
    public String sel(Question question, Map<String,Object> map, HttpSession session) {
        //抽题
        List<Integer> listSin = new ArrayList<>();
        List<Integer> listMu = new ArrayList<>();
        List<Integer> listP = new ArrayList<>();
        service.sel(question,map,listSin,listMu,listP);
        session.setAttribute("singleId",listSin);
        session.setAttribute("multiId",listMu);
        session.setAttribute("panId",listP);
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        map.put("user",user1);
        return "doing";
    }

    @RequestMapping("/sub")
    public String sub(String single, String multi, String pan, HttpSession session) {
        List<Integer> listSin = (List<Integer>) session.getAttribute("singleId");
        List<Integer> listMu = (List<Integer>) session.getAttribute("multiId");
        List<Integer> listPa = (List<Integer>) session.getAttribute("panId");
        User user = (User) session.getAttribute("login");
        User user1 = userService.findByName(user.getName());
        String uuid = UUID.randomUUID().toString();
        int row = service.save(single,multi,pan,listSin,listMu,listPa,user1.getId(),uuid);
        if(row<=0) {
            session.setAttribute("detail","交卷失败");
            return "redirect:/index";
        } else {
            session.setAttribute("detail","交卷成功");
            return "redirect:/index";
        }
    }
}
