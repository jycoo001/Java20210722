package com.jyc.javaWeb.controller;

import com.jyc.javaWeb.entity.Student;
import com.jyc.javaWeb.service.StudentService;
import com.jyc.javaWeb.service.impl.StudentServiceImpl;
import com.jyc.javaWeb.util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentController.service");
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
        System.out.println("StudentController.selectOne");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String pageNumber = req.getParameter("pageNumber");
        Student student = studentService.selectOne(id);
        req.setAttribute("student", student);
        req.setAttribute("pageNumber",pageNumber);
        req.getRequestDispatcher("/jsp/student/student_edit.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentController.update");
        String pageNumber = req.getParameter("pageNumber");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        Integer age = Integer.parseInt(req.getParameter("age"));
        Integer banjiId = Integer.parseInt(req.getParameter("banjiId"));
        Student student = new Student(id, sname, sex, age, banjiId);
        studentService.update(student);
        resp.sendRedirect(req.getContextPath()+"/student?method=selectAll&pageNumber="+pageNumber);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentController.insert");
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        int age = Integer.parseInt(req.getParameter("age"));
        int banjiId = Integer.parseInt(req.getParameter("banjiId"));
        Student student = new Student(sname, sex, age, banjiId);
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int totalCount = studentService.getCount();
        int totalpage = studentService.insert(student, pageSize, totalCount);
        resp.sendRedirect(req.getContextPath()+"/student?pageNumber="+totalpage);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("StudentController.deleteById");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String pageNumber = req.getParameter("pageNumber");
        studentService.deleteById(id);
        resp.sendRedirect(req.getContextPath()+"/student?method=selectAll&pageNumber="+pageNumber);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StudentController.selectAll");
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
        int totalCount = studentService.getCount();
        PageInfo pageInfo = studentService.selectAll(pageNumber, pageSize, totalCount);
        req.setAttribute("pageInfo",pageInfo);
        req.getRequestDispatcher("/jsp/student/student_list.jsp").forward(req,resp);
    }
}
