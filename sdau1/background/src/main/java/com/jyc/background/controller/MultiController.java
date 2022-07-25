package com.jyc.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.background.model.Master;
import com.jyc.background.model.Multi;
import com.jyc.background.model.Single;
import com.jyc.background.service.MasterService;
import com.jyc.background.service.MultiService;
import com.jyc.background.service.SingleService;
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
@RequestMapping("/multi")
public class MultiController {
    private MultiService service;
    private MasterService masterService;
    @Autowired
    public void setService(MultiService service) {
        this.service = service;
    }

    @Autowired
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping("/all")
    public String all(Map<String,Object> map, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer pageSize,@RequestParam(defaultValue = "1") Integer pages, String multi,HttpSession session) {
        if(pageNumber<=0) {
            pageNumber=1;
        } else if(pageNumber>pages) {
            pageNumber=pages;
        }

        Multi multi1 = new Multi();
        if(multi!=null && !multi.trim().equals("")) {
            PageHelper.startPage(pageNumber,pageSize);
            List<Multi> list = service.findByQuestion(multi);
            PageInfo<Multi> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        } else {
            PageHelper.startPage(pageNumber,pageSize);
            List<Multi> list = service.findAll(multi1);
            PageInfo<Multi> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        }
        if(session.getAttribute("detail")!=null) {
            map.put("detail",session.getAttribute("detail"));
            session.removeAttribute("detail");
        }
        map.put("multi",multi);
        return "multi/table";
    }

    @RequestMapping("/toInsert")
    public String insert() {
        return "multi/newsAdd";
    }

    @RequestMapping("/insert")
    public String insert(Multi multi,Map<String,Object> map,HttpSession session) {
        if ((multi.getQuestion()==null || multi.getQuestion().trim().length()<=0) ||
                (multi.getSimpleQuestion()==null || multi.getSimpleQuestion().trim().length()<=0)) {
            map.put("multi",multi);
            map.put("detail","添加失败");
            return "multi/newsAdd";
        }
        multi.setTime(Calendar.getInstance().getTime());
        Master master =(Master) session.getAttribute("login");
        String name = master.getName();
        Master master1 = masterService.findByName(name);
        multi.setMasterId(master1.getId());
        int row = service.insert(multi);
        if (row>0) {
            session.setAttribute("detail","添加成功！");
            return "redirect:/multi/all";
        } else {
            map.put("multi",multi);
            map.put("detail","添加失败！");
            return "multi/newsAdd";
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
        Multi multi = service.findById(id);
        map.put("multi",multi);
        return "multi/update";
    }

    @RequestMapping("/update")
    public String update(Multi multi,Map<String,Object> map,HttpSession session) {
        Master master =(Master) session.getAttribute("login");
        int row =  service.update(multi,master);
        if (row<=0) {
            map.put("multi",multi);
            map.put("detail","修改失败");
            return "multi/update";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/multi/all";
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
