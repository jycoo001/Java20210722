package com.jyc.controller;

import com.jyc.entity.Banji;
import com.jyc.service.BanjiService;
import com.jyc.vo.BanjiTongJi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/banji")
public class BanjiController {

    @Autowired
    private BanjiService banjiService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public List<Banji> selectAll() {
        List<Banji> banjis = banjiService.selectAll();
        System.out.println(banjis);
        return banjis;
    }

    @RequestMapping("/selectBanjiTongji")
    @ResponseBody
    public List<BanjiTongJi> selectbanjitongji(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiController.selectbanjitongji");
        List<BanjiTongJi> list = banjiService.selectBanjiTongji();
        return list;
    }
}
