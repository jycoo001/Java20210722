package com.jyc.javaWeb.dao.impl;

import com.jyc.javaWeb.dao.StudentDao;
import com.jyc.javaWeb.entity.Student;
import com.jyc.javaWeb.util.JDBCUtil;
import com.jyc.javaWeb.vo.StudentBanji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student selectOne(Integer id) {
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from student where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String sname = resultSet.getString("sname");
                String sex = resultSet.getString("sex");
                int age = resultSet.getInt("age");
                int banjiId = resultSet.getInt("banjiId");
                student = new Student(id,sname,sex,age,banjiId);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        return student;
    }

    @Override
    public void update(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update student set sex=?,sname=?,age=?,banjiId=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,student.getSex());
            statement.setString(2,student.getSname());
            statement.setInt(3,student.getAge());
            statement.setInt(4,student.getBanjiId());
            statement.setInt(5,student.getId());
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
    public void insert(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into student(sex,sname,age,banjiId) value(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,student.getSex());
            statement.setString(2,student.getSname());
            statement.setInt(3,student.getAge());
            statement.setInt(4,student.getBanjiId());
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
            String sql = "delete from student where id = ?";
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
    public ArrayList<StudentBanji> selectAll(int offset, int pageSize) {
        ArrayList<StudentBanji> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT s.id as studentId,s.sname as studentSname,s.sex as studentSex,s.age as studentAge,b.Name as banjiName\n" +
                    "FROM student as s INNER JOIN banji as b\n" +
                    "ON s.banjiId=b.id limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,pageSize);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String studentSname = resultSet.getString("studentSname");
                String studentSex = resultSet.getString("studentSex");
                int studentAge = resultSet.getInt("studentAge");
                String banjiName = resultSet.getString("banjiName");
                StudentBanji student = new StudentBanji(studentId, studentSname, studentSex, studentAge, banjiName);
                list.add(student);
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
            String sql = "select count(*) from student";
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
