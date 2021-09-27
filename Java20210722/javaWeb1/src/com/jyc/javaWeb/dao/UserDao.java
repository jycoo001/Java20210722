package com.jyc.javaWeb.dao;

import com.jyc.javaWeb.entity.User;

public interface UserDao {
    public User login(String name, String password);
    public int register(User user);
}
