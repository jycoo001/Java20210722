package com.jyc.controller;

import com.jyc.entity.Teacher;
import com.jyc.service.TeacherService;
import com.jyc.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherConntroller {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/selectAll")
    public String selectAll(Integer pageNumber, Integer pageSize, Model model) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageInfo pageInfo = teacherService.selectAll(pageNumber,pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "teacher/teacher_list";
    }

    @RequestMapping("/insert")
    public String insert(Teacher teacher, Integer pageSize, Model model) {
        int pageNumber = teacherService.insert(teacher, pageSize);
        return "redirect:/teacher/selectAll.action?pageNumber="+pageNumber+"&pageSize="+pageSize;
    }

    @RequestMapping("/selectById")
    public String selectOne(Integer id, Integer pageNumber, Integer pageSize, Model model) {
        Teacher teacher = teacherService.selectOne(id);
        model.addAttribute("teacher", teacher);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        return "teacher/teacher_edit";
    }

    @RequestMapping("/update")
    public String update(Teacher teacher, Integer pageNumber, Integer pageSize, Model model) {
        teacherService.update(teacher);
        return "redirect:/teacher/selectAll.action?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    }

    @RequestMapping("/deleteById")
    public String deleteById(Integer id, Integer pageNumber, Integer pageSize, Model model) {
        teacherService.deleteById(id);
        return "redirect:/teacher/selectAll.action?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
    }
}
