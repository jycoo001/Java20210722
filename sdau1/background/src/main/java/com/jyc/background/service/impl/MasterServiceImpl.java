package com.jyc.background.service.impl;

import com.jyc.background.dao.MasterDAO;
import com.jyc.background.model.Master;
import com.jyc.background.service.MasterService;
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
    public List<Master> findAll(Master master) {
        return dao.findAll(master);
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
    public int deleteMany(List<Integer> list) {
        return dao.deleteMany(list);
    }
}
