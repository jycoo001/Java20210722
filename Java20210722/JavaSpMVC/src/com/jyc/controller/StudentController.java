package com.jyc.controller;

import com.jyc.entity.Student;
import com.jyc.service.StudentService;
import com.jyc.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @RequestMapping("/selectAll")
    //json 加上 @ResponseBody
    public String selectAll(Integer pageNumber, Integer pageSize, Model model) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageInfo pageInfo = studentService.selectAll(pageNumber,pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "student/student_list";
    }

    @RequestMapping("/insert")
    public String insert(Student student, Integer pageSize, Model model) {
        int pageNumber = studentService.insert(student, pageSize);
        return "redirect:/student/selectAll.action?pageNumber="+pageNumber+"&pageSize="+pageSize;
    }

    @RequestMapping("/selectById")
    public String selectOne(Integer id, Integer pageNumber, Integer pageSize, Model model) {
        Student student = studentService.selectOne(id);
        model.addAttribute("student", student);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        return "student/student_edit";
    }

    @RequestMapping("/update")
    public String update(Student student, Integer pageNumber, Integer pageSize, Model model) {
        studentService.update(student);
        return "redirect:/student/selectAll.action?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    }

    @RequestMapping("/deleteById")
    public String deleteById(Integer id, Integer pageNumber, Integer pageSize, Model model) {
        studentService.deleteById(id);
        return "redirect:/student/selectAll.action?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    }
}
