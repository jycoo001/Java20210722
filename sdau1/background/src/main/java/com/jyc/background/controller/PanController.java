package com.jyc.background.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.background.model.Master;
import com.jyc.background.model.Pan;
import com.jyc.background.model.Single;
import com.jyc.background.service.MasterService;
import com.jyc.background.service.PanService;
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
@RequestMapping("/pan")
public class PanController {
    private PanService service;
    private MasterService masterService;
    @Autowired
    public void setService(PanService service) {
        this.service = service;
    }

    @Autowired
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping("/all")
    public String all(Map<String,Object> map, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "5") Integer pageSize,@RequestParam(defaultValue = "1") Integer pages, String pan,HttpSession session) {
        if(pageNumber<=0) {
            pageNumber=1;
        } else if(pageNumber>pages) {
            pageNumber=pages;
        }

        Pan pan1 = new Pan();
        if(pan!=null && !pan.trim().equals("")) {
            PageHelper.startPage(pageNumber,pageSize);
            List<Pan> list = service.findByQuestion(pan);
            PageInfo<Pan> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        } else {
            PageHelper.startPage(pageNumber,pageSize);
            List<Pan> list = service.findAll(pan1);
            PageInfo<Pan> page = new PageInfo<>(list);
            map.put("list",list);
            map.put("page",page);
        }
        if(session.getAttribute("detail")!=null) {
            map.put("detail",session.getAttribute("detail"));
            session.removeAttribute("detail");
        }
        map.put("pan",pan);
        return "pan/table";
    }

    @RequestMapping("/toInsert")
    public String insert() {
        return "pan/newsAdd";
    }

    @RequestMapping("/insert")
    public String insert(Pan pan,Map<String,Object> map,HttpSession session) {
        if ((pan.getQuestion()==null || pan.getQuestion().trim().length()<=0) ||
                (pan.getSimpleQuestion()==null || pan.getSimpleQuestion().trim().length()<=0)) {
            map.put("pan",pan);
            map.put("detail","添加失败");
            return "pan/newsAdd";
        }
        pan.setTime(Calendar.getInstance().getTime());
        Master master =(Master) session.getAttribute("login");
        String name = master.getName();
        Master master1 = masterService.findByName(name);
        pan.setMasterId(master1.getId());
        int row = service.insert(pan);
        if (row>0) {
            session.setAttribute("detail","添加成功！");
            return "redirect:/pan/all";
        } else {
            map.put("pan",pan);
            map.put("detail","添加失败！");
            return "pan/newsAdd";
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
        Pan pan = service.findById(id);
        map.put("pan",pan);
        return "pan/update";
    }

    @RequestMapping("/update")
    public String update(Pan pan,Map<String,Object> map,HttpSession session) {
        Master master =(Master) session.getAttribute("login");
        int row =  service.update(pan,master);
        if (row<=0) {
            map.put("pan",pan);
            map.put("detail","修改失败");
            return "pan/update";
        } else {
            session.setAttribute("detail","修改成功");
            return "redirect:/pan/all";
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
