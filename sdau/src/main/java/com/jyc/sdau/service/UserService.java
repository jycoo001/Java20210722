package com.jyc.sdau.service;

import com.jyc.sdau.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll(User user);

    public User findByName(String name);

    public User login(User user);

    public int register(User user);

    public int update(User user);

    public int delete(Integer id);

    public int deleteMany(List<Integer> list);
}
