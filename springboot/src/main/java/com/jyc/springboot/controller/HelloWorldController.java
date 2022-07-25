package com.jyc.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.springboot.model.Student;
import com.jyc.springboot.model.Teacher.Teacher;
import com.jyc.springboot.service.StudentService;
import com.jyc.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("HelloWorldController.hello");
        System.out.println("hello world!");
    }

    @RequestMapping("/studentlist")
    public String findAll(Map<String,Object> map,@RequestParam(defaultValue = "1") Integer pageNumber,@RequestParam(defaultValue = "15") Integer pageSize) {
        System.out.println("HelloWorldController.findAll");
        if(pageNumber<=0) {
            pageNumber=1;
        }
        if(pageSize==0) {
            pageSize=15;
        }
        PageHelper.startPage(pageNumber,pageSize);
        List<Student> list = studentService.findAl();
        PageInfo<Student> page = new PageInfo<>(list);
        map.put("page",page);
        map.put("pageNumber",pageNumber);
        map.put("students",list);
        return "student/list";
    }

    @RequestMapping("/teacherlist")
    public String findAllTea(@RequestParam(defaultValue = "1")Integer pageNumber,@RequestParam(defaultValue = "20") Integer pageSize,Map<String,Object> map) {
        System.out.println("HelloWorldController.findAllTea");
        PageHelper.startPage(pageNumber,pageSize);
        List<Teacher> list =teacherService.findAll();
        PageInfo<Teacher> page = new PageInfo<>(list);
        map.put("page",page);
        map.put("pageNumber",pageNumber);
        map.put("teachers",list);
        return "teacher/list";
    }
}
