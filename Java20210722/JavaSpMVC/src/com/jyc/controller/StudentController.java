package com.jyc.controller;

import com.jyc.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/selectAll")
    //json 加上 @ResponseBody
    public String selectAll() {
        System.out.println("StudentController.selectAll");
        return "/student_insert";
    }

    /*@RequestMapping("/insert")
    public ModelAndView insert(Student student) {
        System.out.println("StudentController.insert");
        System.out.println(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("/student_info.jsp");
        return modelAndView;
    }*/
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(Student student, Model model) {
        System.out.println("StudentController.insert");
        System.out.println(student);
        model.addAttribute("student", student);
        return "/student_info";
    }

    @RequestMapping("/selectOne")
    public void selectOne(Integer id) {
        System.out.println("StudentController.selectOne");
    }
}
