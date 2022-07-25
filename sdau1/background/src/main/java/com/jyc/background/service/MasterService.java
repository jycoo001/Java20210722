package com.jyc.background.service;

import com.jyc.background.model.Master;

import java.util.List;

public interface MasterService {
    public List<Master> findAll(Master user);

    public Master findByName(String name);

    public Master login(Master master);

    public int register(Master master);

    public int update(Master master);

    public int delete(Integer id);

    public int deleteMany(List<Integer> list);
}
