package com.situ.javaWeb.servlet;

import com.situ.javaWeb.entity.Area;
import com.situ.javaWeb.entity.City;
import com.situ.javaWeb.entity.Province;
import com.situ.javaWeb.util.JDBCUtil;
import com.situ.javaWeb.util.JSONUtil;

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

@WebServlet("/area")
public class AreaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "selectProvince":
                selectProvince(req, resp);
                break;
            case "selectCity":
                selectCity(req, resp);
                break;
            case "selectArea":
                selectArea(req, resp);
                break;
        }
    }

    private void selectArea(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("AreaServlet.selectArea");
        String cityId = req.getParameter("cityId");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Area> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,area from tm_area where city_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(cityId));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String area = resultSet.getString("area");
                Area c = new Area(id, area);
                list.add(c);
            }
            for (Area area : list) {
                System.out.println(area);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        JSONUtil.array2Json(list, resp);
    }

    private void selectCity(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("AreaServlet.selectCity");
        String provinceId = req.getParameter("provinceId");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<City> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,city from tm_city where province_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(provinceId));
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String city = resultSet.getString("city");
                City c = new City(id, city);
                list.add(c);
            }
            for (City city : list) {
                System.out.println(city);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        JSONUtil.array2Json(list, resp);
    }

    private void selectProvince(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("AreaServlet.selectProvince");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Province> list = new ArrayList<>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,province from tm_province";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String province = resultSet.getString("province");
                Province p = new Province(id, province);
                list.add(p);
            }
            for (Province province : list) {
                System.out.println(province);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        JSONUtil.array2Json(list, resp);

    }
}
