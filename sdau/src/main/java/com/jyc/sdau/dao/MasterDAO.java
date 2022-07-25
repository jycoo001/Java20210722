package com.jyc.sdau.dao;

import com.jyc.sdau.model.Master;
import com.jyc.sdau.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterDAO {
    public List<Master> findAll(User user);

    public Master findByName(String name);

    public Master login(Master master);

    public int register(Master master);

    public int update(Master master);

    public int delete(Integer id);

    public int deleteMAny(@Param(value = "id") List<Integer> list);
}
