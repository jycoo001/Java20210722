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
        int n = userDao.selectOne(user.getName());
        if (n == 0) {
            return n;
        } else {
            return userDao.register(user);
        }
    }
}
