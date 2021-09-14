package com.jyc.jdbcest;

import java.sql.*;

public class CreatTes1 {
    public static void main(String[] args) {
        System.out.println("CreatTes1.main");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?" +
                    "characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true" ,
                    "root", "123456");
            //String sql = "select * from student";
            String sql1 = "CREATE TABLE aaa (" +
                    "id INT NOT NULL," +
                    "name VARCHAR(45) NOT NULL," +
                    "PRIMARY KEY(id)" +
                    ")";
            statement = connection.prepareStatement(sql1);
            statement.execute();
            /*while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("sname");
                System.out.println("id: " + id + " sname: " + name);
            }*/
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }

    }
}
