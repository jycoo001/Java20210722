package com.jyc.controller;

import com.jyc.entity.User;
import com.jyc.service.UserService;
import com.jyc.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String code, User user, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        if (!code.equals(sessionCode)) {
            return "/fail";
        }
        String password = MD5Utils.MD5(user.getPassword());
        user.setPassword(password);
        User user1 =  userService.login(user);
        if (user1 != null) {
            session = request.getSession();
            session.setAttribute("user", user1);
            return "/fail";
        }else {
            return "/fail";
        }
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
        int level = 1;
        user.setLevel(level);
        user.setPassword(MD5Utils.MD5(user.getPassword()));
        Boolean b = userService.register(user);
        if (b) {
            return "/login";
        } else {
            return "/fail";
        }
    }

    /*HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");*/
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/login";
    }
}
