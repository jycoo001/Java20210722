package com.situ.javaWeb.servlet;

import com.situ.javaWeb.entity.Student;
import com.situ.javaWeb.util.JDBCUtil;
import com.situ.javaWeb.util.pageInfo;
import com.situ.javaWeb.vo.StudentBanji;

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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentServlet.service");
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
                break;
        }
    }

    private void selectOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        String id = req.getParameter("id");
        String pageNumber = req.getParameter("pageNumber");
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
                int banjiId = resultSet.getInt("banjiId");
                student = new Student(Integer.parseInt(id),sname,sex,age,banjiId);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        req.setAttribute("student", student);
        req.setAttribute("pageNumber",pageNumber);
        req.getRequestDispatcher("/student_edit.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String id = req.getParameter("id");
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String banjiId = req.getParameter("banjiId");
        String pageNumber = req.getParameter("pageNumber");
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update student set sex=?,sname=?,age=?,banjiId=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,sex);
            statement.setString(2,sname);
            statement.setInt(3,Integer.parseInt(age));
            statement.setInt(4,Integer.parseInt(banjiId));
            statement.setInt(5,Integer.parseInt(id));
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("insert count:" + count);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }
        resp.sendRedirect(req.getContextPath()+"/student?method=selectAll&pageNumber="+pageNumber);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        int age = Integer.parseInt(req.getParameter("age"));
        int banjiId = Integer.parseInt(req.getParameter("banjiId"));
        String pageSizeStr = req.getParameter("pageSize");
        int pageSize = Integer.parseInt(pageSizeStr);
       try {
           connection = JDBCUtil.getConnection();
           String sql = "insert into student(sex,sname,age,banjiId) value(?,?,?,?)";
           statement = connection.prepareStatement(sql);
           statement.setString(1,sex);
           statement.setString(2,sname);
           statement.setInt(3,age);
           statement.setInt(4,banjiId);
           System.out.println(statement);
           int count = statement.executeUpdate();
           System.out.println("insert count:" + count);
       }catch (SQLException throwables) {
           throwables.printStackTrace();
       }finally {
           JDBCUtil.closepre(connection,statement,null);
       }
        int totalCount = getCount();
        int totalpage = (int)Math.ceil((double)totalCount / pageSize);
       resp.sendRedirect(req.getContextPath()+"/student?pageNumber="+totalpage);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String pageNumber = req.getParameter("pageNumber");
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
        resp.sendRedirect(req.getContextPath()+"/student?method=selectAll&pageNumber="+pageNumber);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<StudentBanji> list = new ArrayList<>();
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
            String sql = "SELECT s.id as studentId,s.sname as studentSname,s.sex as studentSex,s.age as studentAge,b.Name as banjiName\n" +
                    "FROM student as s INNER JOIN banji as b\n" +
                    "ON s.banjiId=b.id limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,pageSize);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String studentSname = resultSet.getString("studentSname");
                String studentSex = resultSet.getString("studentSex");
                int studentAge = resultSet.getInt("studentAge");
                String banjiName = resultSet.getString("banjiName");
                StudentBanji student = new StudentBanji(studentId, studentSname, studentSex, studentAge, banjiName);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closest(connection, statement, resultSet);
        }
        int totalCount = getCount();
        int totalpage = (int)Math.ceil((double)totalCount / pageSize);
        pageInfo pageInfo1 = new pageInfo(list,pageNumber,totalpage,pageSize);
        System.out.println(pageInfo1);
        req.setAttribute("pageInfo",pageInfo1);
        req.getRequestDispatcher("/student_list.jsp").forward(req,resp);
    }

    private int getCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from student";
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
