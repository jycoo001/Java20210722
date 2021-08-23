package com.situ.javaWeb.servlet;

import com.situ.javaWeb.entity.Teacher;
import com.situ.javaWeb.util.JDBCUtil;
import com.situ.javaWeb.util.pageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherServlet.service");
        //req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        if (method == null||method.equals("")){
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
        Teacher teacher = null;
        String id = req.getParameter("id");
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from teacher where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(id));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                teacher = new Teacher(Integer.parseInt(id),name,age,address);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("teacher_edit.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String age = req.getParameter("age");
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update teacher set name=?,address=?,age=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,address);
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
        resp.sendRedirect(req.getContextPath()+"/teacher");
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String age = req.getParameter("age");
        if(name==null||address==null||age==null||name.equals("")||address.equals("")||age.equals("")) {
            resp.sendRedirect(req.getContextPath()+"/teacher");
        }
       try {
           connection = JDBCUtil.getConnection();
           String sql = "insert into teacher(name,address,age) value(?,?,?)";
           statement = connection.prepareStatement(sql);
           statement.setString(1,name);
           statement.setString(2,address);
           statement.setInt(3,Integer.parseInt(age));
           System.out.println(statement);
           int count = statement.executeUpdate();
           System.out.println("insert count:" + count);
       }catch (SQLException throwables) {
           throwables.printStackTrace();
       }finally {
           JDBCUtil.closepre(connection,statement,null);
       }

       resp.sendRedirect(req.getContextPath()+"/teacher");

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from teacher where id = ?";
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

        resp.sendRedirect(req.getContextPath()+"/teacher");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Teacher> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String pageNumStr = req.getParameter("pageNumber");
        String pageSStr = req.getParameter("pageSize");
        if (pageNumStr == null || pageNumStr.equals("")) {
            pageNumStr = "1";
        }
        if (pageSStr == null || pageSStr.equals("")) {
            pageSStr = "5";
        }
        int pageNumber = Integer.parseInt(pageNumStr);
        int pageSize = Integer.parseInt(pageSStr);
        int offset = (pageNumber-1) * pageSize;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,address,age from teacher limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,pageSize);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                Teacher teacher = new Teacher(id,name,age,address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closest(connection, statement, resultSet);
        }
        int totalCount = getCount();
        int totalpage = (int)Math.ceil((double)totalCount / pageSize);
        pageInfo pageInfo1 = new pageInfo(list,pageNumber,totalpage,pageSize);
        req.setAttribute("pageInfo1",pageInfo1);
        req.getRequestDispatcher("/teacher_list.jsp").forward(req,resp);
    }

    private int getCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from teacher";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        return totalCount;
    }
}
