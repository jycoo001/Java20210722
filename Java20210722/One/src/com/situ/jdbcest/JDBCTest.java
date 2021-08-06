package com.situ.jdbcest;

import org.junit.Test;

import javax.xml.transform.Result;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class JDBCTest {
    @Test
    public void test1 () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?" +
                    "characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true",
                    "root","123456");
            String sql = "select * from student";
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(sql);
            ArrayList<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String sname = resultSet.getString("sname");
                String sex = resultSet.getString("sex");
                int age = resultSet.getInt("age");
                Student student = new Student(id,sname,sex,age);
                list.add(student);
            }
            for (Student student : list) {
                System.out.println(student);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void inse (ResultSet resultSet) {

    }
}
