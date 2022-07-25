package com.jyc.forward.dao;

import com.jyc.forward.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    public List<User> findAll(User user);

    public User findByName(String name);

    public User login(User user);

    public int register(User user);

    public int update(User user);

    public int delete(Integer id);

    public int deleteMAny(@Param(value = "id") List<Integer> list);

    public User findById(Integer id);
}
