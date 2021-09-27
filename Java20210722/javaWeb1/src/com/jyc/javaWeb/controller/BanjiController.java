package com.jyc.javaWeb.controller;

import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.service.BanjiService;
import com.jyc.javaWeb.service.impl.BanjiServiceImpl;
import com.jyc.javaWeb.util.JSONUtil;
import com.jyc.javaWeb.vo.BanjiTongJi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/banji")
public class BanjiController extends HttpServlet {
    private BanjiService banjiService = new BanjiServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiController.service");
        String method = req.getParameter("method");
        switch (method) {
            case "selectAll":
                selectAllNoPage(req, resp);
                break;
            case "selectbanjitongji":
                selectbanjitongji(req, resp);
                break;
        }
    }

    private void selectbanjitongji(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiController.selectbanjitongji");
        List<BanjiTongJi> list = banjiService.selectbanjitongji();
        JSONUtil.array2Json(list, resp);
    }

    private void selectAllNoPage(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiController.selectAll");
        List<Banji> list = banjiService.selectAllNoPage();
        JSONUtil.array2Json(list, resp);
    }
}
