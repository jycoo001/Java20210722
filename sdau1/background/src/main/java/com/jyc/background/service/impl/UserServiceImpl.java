package com.jyc.background.service.impl;

import com.jyc.background.dao.UserDAO;
import com.jyc.background.model.User;
import com.jyc.background.service.UserService;
import com.jyc.background.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
    public List<User> findByUser(String user) {
        user = "%"+user+"%";
        return dao.findByUser(user);
    }

    @Override
    public int insert(User user) {
        user.setLoginTime(Calendar.getInstance().getTime());
        user.setPassword("123");
        user.setViolation(0);
        user.setIsExam("否");
        return dao.insert(user);
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
    public int deleteMany(Integer[] id) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < id.length; i++) {
            list.add(id[i]);
        }
        return dao.deleteMany(list);
    }

    @Override
    public User findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public int updateStatus(Integer id) {
        User user = new User();
        user.setId(id);
        List<User> list = dao.findAll(user);
        User user1 = list.get(0);
        String status = user1.getStatus();
        if(status.equals("是")) {
            user1.setStatus("否");
        } else {
            user1.setStatus("是");
        }
        user1.setPassword("");
        int row = dao.update(user1);
        return row;
    }
}
