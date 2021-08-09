package com.situ.jdbcest;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBCTest1 {
    @Test
    public void test1 () throws SQLException {
        System.out.println("JDBCTest1.test1");
        Connection connection = null;
        //Statement statement;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int setId = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入id");
        setId = sc.nextInt();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?" +
                            "characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true",
                    "root","123456");
            String sql = "select * from student where id >= ?";
            //statement = connection.createStatement();
            //resultSet =  statement.executeQuery(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,setId);
            resultSet = preparedStatement.executeQuery();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }
    }
}
