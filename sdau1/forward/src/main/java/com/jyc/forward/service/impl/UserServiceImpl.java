package com.jyc.forward.service.impl;

import com.jyc.forward.dao.UserDAO;
import com.jyc.forward.model.User;
import com.jyc.forward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO dao;
    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<User> findAll(User user) {
        return dao.findAll(user);
    }

    @Override
    public User findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }

    @Override
    public int register(User user) {
        return dao.register(user);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int deleteMany(List<Integer> list) {
        return dao.deleteMAny(list);
    }
}
