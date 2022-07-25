package com.jyc.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.background.model.ExamSet;
import com.jyc.background.model.UserExam;
import com.jyc.background.model.UserPractice;
import com.jyc.background.service.ExamService;
import com.jyc.background.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exam")
public class ExamController {
    private ExamService service;
    @Autowired
    public void setService(ExamService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public String all(Map<String,Object> map, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pages, String userExam, HttpSession session) {
        if(pageNumber<=0) {
            pageNumber=1;
        } else if(pageNumber>pages) {
            pageNumber=pages;
        }

        UserExam userExam1 = new UserExam();
        if(userExam!=null && !userExam.trim().equals("")) {
            PageHelper.startPage(pageNumber,pageSize);
            List<UserExam> list = service.findByUserExam(userExam);
            PageInfo<UserExam> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        } else {
            PageHelper.startPage(pageNumber,pageSize);
            List<UserExam> list = service.findAll(userExam1);
            PageInfo<UserExam> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        }
        if(session.getAttribute("detail")!=null) {
            map.put("detail",session.getAttribute("detail"));
            session.removeAttribute("detail");
        }
        map.put("userExam",userExam);
        return "userExam/table";
    }

    @RequestMapping("/toInsert")
    public String insert() {
        return "userExam/newsAdd";
    }

    @RequestMapping("/insert")
    public String insert(ExamSet examSet, Map<String,Object> map, HttpSession session) {
        int row = service.insert(examSet);
        if (row>0) {
            session.setAttribute("detail","添加成功！");
            return "redirect:/userExam/all";
        } else {
            map.put("examSet",examSet);
            map.put("detail","添加失败！");
            return "userExam/newsAdd";
        }
    }

    @RequestMapping("/toUpdate")
    public String to(@RequestParam("id") Integer id,Map<String,Object> map) {
        UserExam userExam = service.findById(id);
        map.put("userExam",userExam);
        return "userExam/update";
    }

    @RequestMapping("/update")
    public String update(UserExam userExam, Map<String,Object> map, HttpSession session) {
        int row =  service.update(userExam);
        if (row<=0) {
            map.put("userExam",userExam);
            map.put("detail","修改失败");
            return "userExam/update";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/userExam/all";
        }
    }

    @RequestMapping(value = "/delete",produces = "application/json")
    @ResponseBody
    public Map<String,Object> del(@RequestParam(name = "id") Integer id) {
        //RequestParam不支持post请求
        Map<String,Object> map = new HashMap<>();
        int row = service.delete(id);
        if(row<=0) {
            map.put("detail","删除失败");
        } else {
            map.put("detail","删除成功");
        }
        return map;
    }

    @RequestMapping(value = "/deleteMany",produces = "application/json")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam(name = "ids[]") Integer[] id) {
        //RequestParam不支持post请求
        Map<String,Object> map = new HashMap<>();
        int row = service.deleteMany(id);
        if(row<=0) {
            map.put("detail","删除失败");
        } else {
            map.put("detail","删除成功");
        }
        return map;
    }
}
