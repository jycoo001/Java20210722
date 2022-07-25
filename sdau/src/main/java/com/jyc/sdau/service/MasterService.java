package com.jyc.sdau.service;

import com.jyc.sdau.model.Master;
import com.jyc.sdau.model.User;

import java.util.List;

public interface MasterService {
    public List<Master> findAll(User user);

    public Master findByName(String name);

    public Master login(Master master);

    public int register(Master master);

    public int update(Master master);

    public int delete(Integer id);

    public int deleteMAny(List<Integer> list);
}
