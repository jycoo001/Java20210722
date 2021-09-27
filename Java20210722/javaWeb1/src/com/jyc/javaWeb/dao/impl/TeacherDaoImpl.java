package com.jyc.javaWeb.dao.impl;

import com.jyc.javaWeb.dao.TeacherDao;
import com.jyc.javaWeb.entity.Teacher;
import com.jyc.javaWeb.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public Teacher selectOne(Integer id) {
        Teacher teacher = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from teacher where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                teacher = new Teacher(id,name,age,address);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update teacher set name=?,address=?,age=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getAddress());
            statement.setInt(3,teacher.getAge());
            statement.setInt(4,teacher.getId());
            System.out.println(statement);
            count = statement.executeUpdate();
            System.out.println("update count:" + count);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }

    }

    @Override
    public void insert(Teacher teacher) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into teacher(name,address,age) value(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,teacher.getName());
            statement.setString(2,teacher.getAddress());
            statement.setInt(3,teacher.getAge());
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("insert count:" + count);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from teacher where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            System.out.println(statement);
            int count = statement.executeUpdate();
            System.out.println("delete count:" + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,null);
        }
    }

    @Override
    public ArrayList<Teacher> selectAll(int offset, int pageSize) {
        ArrayList<Teacher> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,address,age from teacher limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,pageSize);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                Teacher teacher = new Teacher(id,name,age,address);
                list.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closest(connection, statement, resultSet);
        }
        return list;
    }

    @Override
    public int getCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int totalCount = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from teacher";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalCount = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection, statement, resultSet);
        }
        return totalCount;
    }
}
