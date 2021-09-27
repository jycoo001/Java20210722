package com.jyc.javaWeb.service.impl;

import com.jyc.javaWeb.dao.UserDao;
import com.jyc.javaWeb.dao.impl.UserDaoImpl;
import com.jyc.javaWeb.entity.User;
import com.jyc.javaWeb.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String name, String password) {
        return userDao.login(name, password);
    }

    @Override
    public int register(User user) {
        return userDao.register(user);
    }
}
