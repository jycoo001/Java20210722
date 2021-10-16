package com.jyc.service;

import com.jyc.entity.User;

public interface UserService {
    public User login(User user);
    public Boolean register(User user);
}
