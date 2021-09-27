package com.jyc.javaWeb.controller;

import com.jyc.javaWeb.entity.Teacher;
import com.jyc.javaWeb.service.TeacherService;
import com.jyc.javaWeb.service.impl.TeacherServiceImpl;
import com.jyc.javaWeb.util.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher")
public class TeacherController extends HttpServlet {
    private TeacherService teacherService = new TeacherServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherController.service");
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
        System.out.println("TeacherController.selectOne");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String pageNumber = req.getParameter("pageNumber");
        Teacher teacher = teacherService.selectOne(id);
        req.setAttribute("teacher", teacher);
        req.setAttribute("pageNumber",pageNumber);
        req.getRequestDispatcher("/jsp/teacher/teacher_edit.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherController.update");
        String pageNumber = req.getParameter("pageNumber");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Integer age = Integer.parseInt(req.getParameter("age"));
        Teacher teacher = new Teacher(id, name, age, address);
        teacherService.update(teacher);
        resp.sendRedirect(req.getContextPath()+"/teacher?method=selectAll&pageNumber="+pageNumber);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherController.insert");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Integer age = Integer.parseInt(req.getParameter("age"));
        if(name==null||address==null||age==null||name.equals("")||address.equals("")||age.equals("")) {
            resp.sendRedirect(req.getContextPath()+"/teacher");
        }
        Teacher teacher = new Teacher(name, age, address);
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int totalCount = teacherService.getCount();
        int totalPage = teacherService.insert(teacher, pageSize, totalCount);
        resp.sendRedirect(req.getContextPath()+"/teacher?pageNumber="+totalPage);

    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("TeacherController.deleteById");
        Integer id = Integer.parseInt(req.getParameter("id"));
        String pageNumber = req.getParameter("pageNumber");
        teacherService.deleteById(id);
        resp.sendRedirect(req.getContextPath()+"/teacher?pageNumber="+pageNumber);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TeacherController.selectAll");
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
        int totalCount = teacherService.getCount();
        PageInfo pageInfo = teacherService.selectAll(pageNumber, pageSize, totalCount);
        req.setAttribute("pageInfo",pageInfo);
        req.getRequestDispatcher("/jsp/teacher/teacher_list.jsp").forward(req,resp);
    }
}
