package com.jyc.javaWeb.dao.impl;

import com.jyc.javaWeb.dao.BanjiDao;
import com.jyc.javaWeb.entity.Banji;
import com.jyc.javaWeb.util.JDBCUtil;
import com.jyc.javaWeb.vo.BanjiTongJi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanjiDaoImpl implements BanjiDao {
    @Override
    public Banji selectOne(Integer id) {
        Banji banji = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from banji where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                banji = new Banji(id,name);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtil.closepre(connection,statement,resultSet);
        }
        return banji;
    }

    @Override
    public void update(Banji banji) {
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update banji set name=? where id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,banji.getName());
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
    public void insert(Banji banji) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into banji(name) value(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,banji.getName());
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
            String sql = "delete from banji where id = ?";
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
    public ArrayList<Banji> selectAll(int offset, int pageSize) {
        ArrayList<Banji> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name FROM banji limit ?,?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,pageSize);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji student = new Banji(id, name);
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
            String sql = "select count(*) from banji";
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

    @Override
    public List<BanjiTongJi> selectbanjitongji() {
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
        return list;
    }

    @Override
    public List<Banji> selectAllNoPage() {
        ArrayList<Banji> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT id,name FROM banji";
            statement = connection.prepareStatement(sql);
            System.out.println(statement);
            resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Banji student = new Banji(id, name);
                list.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closest(connection, statement, resultSet);
        }
        return list;
    }
}
