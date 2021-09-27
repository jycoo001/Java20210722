package com.jyc.javaWeb.service;

import com.jyc.javaWeb.entity.User;

public interface UserService {
    public User login(String name, String password);
    public int register(User user);
}
