package com.jyc.background.controller;

import com.jyc.background.model.Admin;
import com.jyc.background.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/personInfo")
    public String my(Map<String,Object> map, HttpSession session) {
        Admin admin = adminService.findByName((String)session.getAttribute("nae"));
        map.put("admin",admin);
        return "personInfo";
    }
}
