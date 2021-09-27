package com.jyc.javaWeb.controller;

import com.jyc.javaWeb.entity.User;
import com.jyc.javaWeb.service.UserService;
import com.jyc.javaWeb.service.impl.UserServiceImpl;
import com.jyc.javaWeb.util.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserController.service");
        String method = req.getParameter("method");
        switch (method) {
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "register":
                register(req, resp);
                break;
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserController.register");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        if (!sessionCode.equals(code)) {
            resp.sendRedirect(req.getContextPath()+"/register.jsp");
            return;
        }
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Integer age = Integer.parseInt(req.getParameter("age"));
        int level = 1;
        String password1 = MD5Utils.MD5(password);
        User user = new User(name, password, age, level);
        int n = userService.register(user);
        if (n != 0) {
            System.out.println("注册成功！");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }else {
            resp.sendRedirect(req.getContextPath()+"/fail.jsp");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserController.logout");
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("UserController.login");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        if (!sessionCode.equals(code)) {
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }
        User user = null;
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String password1 = MD5Utils.MD5(password);
        user = userService.login(name, password1);
        if (user!=null) {
            session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath()+"/fail.jsp");
        }
    }
}
