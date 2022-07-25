package com.jyc.background.service;

import com.jyc.background.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll(User user);

    public List<User> findByUser(String user);

    public int insert(User user);

    public int update(User user);

    public int delete(Integer id);

    public int deleteMany(Integer[] id);

    public User findById(Integer id);

    public int updateStatus(Integer id);
}
