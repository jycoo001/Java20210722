package com.jyc.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.background.model.Master;
import com.jyc.background.model.Single;
import com.jyc.background.model.UserPractice;
import com.jyc.background.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {
    private TestService service;
    @Autowired
    public void setService(TestService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public String all(Map<String,Object> map, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pages, String userPractice, HttpSession session) {
        if(pageNumber<=0) {
            pageNumber=1;
        } else if(pageNumber>pages) {
            pageNumber=pages;
        }

        UserPractice userPractice1 = new UserPractice();
        if(userPractice!=null && !userPractice.trim().equals("")) {
            PageHelper.startPage(pageNumber,pageSize);
            List<UserPractice> list = service.findByUserPractice(userPractice);
            PageInfo<UserPractice> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        } else {
            PageHelper.startPage(pageNumber,pageSize);
            List<UserPractice> list = service.findAll(userPractice1);
            PageInfo<UserPractice> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        }
        if(session.getAttribute("detail")!=null) {
            map.put("detail",session.getAttribute("detail"));
            session.removeAttribute("detail");
        }
        map.put("userPractice",userPractice);
        return "userPractice/table";
    }

    @RequestMapping("/toInsert")
    public String insert() {
        return "userPractice/newsAdd";
    }

    @RequestMapping("/insert")
    public String insert(UserPractice userPractice, Map<String,Object> map, HttpSession session) {
        int row=1;
        if (row>0) {
            session.setAttribute("detail","添加成功！");
            return "redirect:/userPractice/all";
        } else {
            map.put("userPractice",userPractice);
            map.put("detail","添加失败！");
            return "userPractice/newsAdd";
        }
    }

    @RequestMapping("/toUpdate")
    public String to(@RequestParam("id") Integer id,Map<String,Object> map) {
        UserPractice userPractice = service.findById(id);
        map.put("userPractice",userPractice);
        return "userPractice/update";
    }

    @RequestMapping("/update")
    public String update(UserPractice userPractice,Map<String,Object> map,HttpSession session) {
        int row =  1;
        if (row<=0) {
            map.put("userPractice",userPractice);
            map.put("detail","修改失败");
            return "userPractice/update";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/userPractice/all";
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
