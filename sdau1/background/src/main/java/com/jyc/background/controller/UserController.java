package com.jyc.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.background.model.Master;
import com.jyc.background.model.Single;
import com.jyc.background.model.User;
import com.jyc.background.service.MasterService;
import com.jyc.background.service.SingleService;
import com.jyc.background.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private UserService service;
    private MasterService masterService;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Autowired
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping("/all")
    public String all(Map<String,Object> map, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer pageSize,@RequestParam(defaultValue = "1") Integer pages, String user,HttpSession session) {
        if(pageNumber<=0) {
            pageNumber=1;
        } else if(pageNumber>pages) {
            pageNumber=pages;
        }
        User user1 = new User();
        if(user!=null && !user.trim().equals("")) {
            PageHelper.startPage(pageNumber,pageSize);
            List<User> list = service.findByUser(user);
            PageInfo<User> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        } else {
            PageHelper.startPage(pageNumber,pageSize);
            List<User> list = service.findAll(user1);
            PageInfo<User> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        }
        if(session.getAttribute("detail")!=null) {
            map.put("detail",session.getAttribute("detail"));
            session.removeAttribute("detail");
        }
        map.put("user",user);
        return "user/table";
    }

    @RequestMapping("/toInsert")
    public String insert() {
        return "user/newsAdd";
    }

    @RequestMapping("/insert")
    public String insert(User user,Map<String,Object> map,HttpSession session) {
        if ((user.getName()==null || user.getName().trim().length()<=0) ||
                (user.getPhone()==null || user.getPhone().trim().length()<=0)) {
            map.put("user",user);
            map.put("detail","添加失败");
            return "user/newsAdd";
        }
        int row = service.insert(user);
        if (row>0) {
            session.setAttribute("detail","添加成功！");
            return "redirect:/user/all";
        } else {
            map.put("user",user);
            map.put("detail","添加失败！");
            return "user/newsAdd";
        }
    }

    @RequestMapping(value = "/updateStatus",produces = "application/json")
    @ResponseBody
    public Map<String,Object> up(Integer id) {
        Map<String,Object> map = new HashMap<>();
        int row = service.updateStatus(id);
        if(row<=0) {
            map.put("detail","修改失败");
        } else {
            map.put("detail","修改成功!修改了"+row+"行!");
        }
        return map;
    }

    @RequestMapping("/toUpdate")
    public String to(@RequestParam("id") Integer id,Map<String,Object> map) {
        User user = service.findById(id);
        map.put("user",user);
        return "user/update";
    }

    @RequestMapping("/update")
    public String update(User user,Map<String,Object> map,HttpSession session) {
        int row =  service.update(user);
        if (row<=0) {
            map.put("user",user);
            map.put("detail","修改失败");
            return "user/update";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/user/all";
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
