package com.jyc.background.controller;

import com.jyc.background.model.Master;
import com.jyc.background.service.MasterService;
import com.jyc.background.util.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class MasterController {

    private MasterService service;
    @Autowired
    public void setService(MasterService service) {
        this.service = service;
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(Master master, String code, HttpSession session, Map<String,Object> map) {
        if(code==null || code.trim().equals("")) {
            map.put("detail","验证码为空！");
            return "login";
        }
        //验证码一分钟失效
        String code1 = "" + session.getAttribute("verification");
        if(!code1.equals(code)) {
            map.put("detail","验证码输入有误！");
            return "login";
        }
        //令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(master.getName(),master.getMiPassword());
        //设置i
        usernamePasswordToken.setRememberMe(true);
        //认证
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            subject.getSession().setTimeout(1000*60*10);
            master.setLoginTime(Calendar.getInstance().getTime());
            int row = service.update(master);
            if(row>0) {
                map.put("detail","登陆成功！");
                session.setAttribute("login",master);
                return "redirect:/index";
            } else {
                map.put("detail","登陆失败！");
                System.out.println("登陆失败！");
                return "login";
            }
        } catch (UnknownAccountException e) {
            map.put("detail","用户不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            map.put("detail","密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/background/toLogin";
    }

    @RequestMapping("/personInfo")
    public String persionInfo(HttpSession session,Map<String,Object> map) {
        Master master = (Master) session.getAttribute("login");
        String name = master.getName();
        Master master1 = service.findByName(name);
        map.put("master",master1);
        if(session.getAttribute("detail")!=null) {
            Object obj = session.getAttribute("detail");
            session.removeAttribute("detail");
            map.put("detail",obj);
        }
        return "personInfo";
    }

    @RequestMapping(value = "/upload",produces = "application/json")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile pic) {
        Map<String,Object> res = new HashMap<>();
        if(pic.getOriginalFilename()!=null && pic.getOriginalFilename().trim().length()>0) {
            String uuid = UUID.randomUUID().toString();
            int idx = pic.getOriginalFilename().lastIndexOf(".");
            String name = uuid + "." + pic.getOriginalFilename().substring(idx + 1);
            String url = Constant.PICTURE_URL + "/master/" + name;
            File file = new File(url);
            try {
                pic.transferTo(file);
                System.out.println("已添加");
                res.put("url","/master/"+name);
                res.put("success","上传成功");
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                res.put("error","上传失败");
            }
        } else {
            res.put("error","上传失败");
        }
        return res;
    }

    @RequestMapping("/update")
    public String update(Master master,Map<String,Object> map,HttpSession session) {
        int rows = service.update(master);
        if(rows<=0) {
            map.put("detail","修改失败");
            map.put("master",master);
            return "personInfo";
        }
        session.setAttribute("detail","修改成功");
        return "redirect:/personInfo";
    }

    @RequestMapping("/changepwd")
    public String changepwd(HttpSession session,Map<String,Object> map) {
        Master master = (Master) session.getAttribute("login");
        String name = master.getName();
        map.put("name",name);
        if(session.getAttribute("detail")!=null) {
            Object obj = session.getAttribute("detail");
            session.removeAttribute("detail");
            map.put("detail",obj);
        }
        return "changepwd";
    }

    @RequestMapping("/updatePassword")
    public String updatePwd(String passwordOld,String passwordNew,String passwordNew1,Map<String,Object> map,HttpSession session) {
        Master master = (Master)session.getAttribute("login");
        master.setPassword(passwordOld);
        Master master1 = service.findByName(master.getName());
        if(master1.getPassword().equals(master.getMiPassword())) {
            if(passwordNew.equals(passwordNew1)) {
                master1.setPassword(passwordNew);
                int row = service.update(master1);
                if(row<=0) {
                    map.put("detail","修改失败");
                    map.put("name",master.getName());
                    map.put("passwordOld",passwordOld);
                    map.put("passwordNew",passwordNew);
                    map.put("passwordNew1",passwordNew1);
                    return "changepwd";
                } else {
                    session.setAttribute("detail","修改成功");
                    return "redirect:/changepwd";
                }
            } else {
                map.put("detail","两次密码不一致！");
                map.put("name",master.getName());
                map.put("passwordOld",passwordOld);
                map.put("passwordNew",passwordNew);
                map.put("passwordNew1",passwordNew1);
                return "changepwd";
            }
        } else {
            map.put("detail","密码错误");
            map.put("name",master.getName());
            map.put("passwordOld",passwordOld);
            map.put("passwordNew",passwordNew);
            map.put("passwordNew1",passwordNew1);
            return "changepwd";
        }
    }
}
