package com.jyc.javaWeb.servlet;

import com.jyc.javaWeb.entity.User;
import com.jyc.javaWeb.util.JDBCUtil;
import com.jyc.javaWeb.util.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserServlet.service");
        req.setCharacterEncoding("UTF-8");
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
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        if (!sessionCode.equals(code)) {
            resp.sendRedirect(req.getContextPath()+"/register.jsp");
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        int n = 0;
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        int age = 20;
        int level = 1;
        String password1 = MD5Utils.MD5(password);
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into user(name, password, age, level) value(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password1);
            statement.setInt(3, age);
            statement.setInt(4, level);
            System.out.println(statement);
            n = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        if (n != 0) {
            System.out.println("注册成功！");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }else {
            resp.sendRedirect(req.getContextPath()+"/fail.jsp");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("UserServlet.logout");
        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        if (!sessionCode.equals(code)) {
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String password1 = MD5Utils.MD5(password);
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,level from user where name=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password1);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                int level = resultSet.getInt("level");
                user = new User(id, name, password, age, level);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        if (user!=null) {
            session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath()+"/fail.jsp");
        }
    }
}
