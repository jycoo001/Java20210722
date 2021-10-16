package com.jyc.service.impl;

import com.jyc.entity.User;
import com.jyc.mapper.UserMapper;
import com.jyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public Boolean register(User user) {
        User user1 = userMapper.selectAll(user.getName());
        if (user1 == null) {
            userMapper.register(user);
            return true;
        }else {
            return false;
        }
    }
}
