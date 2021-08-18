package com.situ.javaWeb.servlet;

import com.situ.javaWeb.entity.Student;
import com.situ.javaWeb.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.service");
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method == null||method == ""){
            method = "selectAll";
        }
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "deleteById":
                deleteById(req, resp);
                break;
            case "insert":
                insert(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "selectOne":
                selectOne(req, resp);
        }
    }

    private void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        String id = req.getParameter("id");
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from student where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(id));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String sname = resultSet.getString("sname");
                String sex = resultSet.getString("sex");
                int age = resultSet.getInt("age");
                student = new Student(Integer.parseInt(id),sname,sex,age);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        req.setAttribute("student", student);
        req.getRequestDispatcher("student_edit.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String id = req.getParameter("id");
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update student set sex=?,sname=?,age=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,sex);
            statement.setString(2,sname);
            statement.setInt(3,Integer.parseInt(age));
            statement.setInt(4,Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("insert count:" + count);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }
        resp.sendRedirect(req.getContextPath()+"/student");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        if(sname==null||sex==null||age==null||sname==""||sex==""||age=="") {
            resp.sendRedirect(req.getContextPath()+"/student");
        }
       try {
           connection = JDBCUtil.getConnection();
           String sql = "insert into student(sex,sname,age) value(?,?,?)";
           statement = connection.prepareStatement(sql);
           statement.setString(1,sex);
           statement.setString(2,sname);
           statement.setInt(3,Integer.parseInt(age));
           System.out.println(statement);
           int count = statement.executeUpdate();
           System.out.println("insert count:" + count);
       }catch (SQLException throwables) {
           throwables.printStackTrace();
       }finally {
           JDBCUtil.closepre(connection,statement,null);
       }

       resp.sendRedirect(req.getContextPath()+"/student");

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from student where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("delete count:" + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }

        resp.sendRedirect(req.getContextPath()+"/student");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Student> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from student";
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String sname = resultSet.getString("sname");
                String sex = resultSet.getString("sex");
                int age = resultSet.getInt("age");
                Student student = new Student(id,sname,sex,age);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closest(connection, statement, resultSet);
        }
        req.setAttribute("list",list);
        req.getRequestDispatcher("/student_list.jsp").forward(req,resp);
    }
}
