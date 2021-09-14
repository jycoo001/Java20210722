package com.jyc.javaWeb.servlet;

import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.util.JDBCUtil;
import com.jyc.javaWeb.util.JSONUtil;
import com.jyc.javaWeb.vo.BanjiTongJi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/banji")
public class BanjiServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BanjiServlet.service");
        String method = req.getParameter("method");
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
            case "selectbanjitongji":
                selectbanjitongji(req, resp);
                break;
        }
    }

    private void selectbanjitongji(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiServlet.selectbanjitongji");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<BanjiTongJi> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select b.name as name, count(*) as count" +
                    " from student as s inner join banji as b" +
                    " on s.banjiId=b.id" +
                    " group by b.id";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int count = resultSet.getInt("count");

                BanjiTongJi banjiTongJi = new BanjiTongJi(name, count);
                list.add(banjiTongJi);
            }
            for (BanjiTongJi banjiTongJi : list) {
                System.out.println(banjiTongJi);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        JSONUtil.array2Json(list, resp);
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("BanjiServlet.selectAll");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Banji> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name from banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji banji = new Banji(id, name);
                list.add(banji);
            }
            for (Banji banji : list) {
                System.out.println(banji);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        JSONUtil.array2Json(list, resp);
    }
}
