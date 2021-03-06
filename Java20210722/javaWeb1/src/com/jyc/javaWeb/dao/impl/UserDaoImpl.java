package com.jyc.javaWeb.dao.impl;

import com.jyc.javaWeb.dao.UserDao;
import com.jyc.javaWeb.entity.User;
import com.jyc.javaWeb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(String name, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,password,age,level from user where name=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                int level = resultSet.getInt("level");
                user = new User(id, name, password, age, level);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public int register(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int n = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into user(name, password, age, level) value(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            statement.setInt(3, user.getAge());
            statement.setInt(4, user.getLevel());
            System.out.println(statement);
            n = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        return n;
    }

    @Override
    public int selectOne(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int n = 1;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from user where name=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                n=0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        return n;
    }
}
