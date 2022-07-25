package com.jyc.sdau.service.impl;

import com.jyc.sdau.dao.MasterDAO;
import com.jyc.sdau.model.Master;
import com.jyc.sdau.model.User;
import com.jyc.sdau.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    private MasterDAO dao;

    @Autowired
    public void setDao(MasterDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Master> findAll(User user) {
        return dao.findAll(user);
    }

    @Override
    public Master findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Master login(Master master) {
        return dao.login(master);
    }

    @Override
    public int register(Master master) {
        return dao.register(master);
    }

    @Override
    public int update(Master master) {
        return dao.update(master);
    }

    @Override
    public int delete(Integer id) {
        return dao.delete(id);
    }

    @Override
    public int deleteMAny(List<Integer> list) {
        return dao.deleteMAny(list);
    }
}
