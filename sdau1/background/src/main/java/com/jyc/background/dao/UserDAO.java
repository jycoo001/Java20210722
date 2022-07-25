package com.jyc.background.dao;

import com.jyc.background.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    public List<User> findAll(User user);

    public List<User> findByUser(String user);

    public Integer findCount();

    public int insert(User user);

    public int update(User user);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "id") List<Integer> list);

    public User findById(Integer id);
}
