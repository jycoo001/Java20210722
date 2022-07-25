package com.jyc.background.dao;

import com.jyc.background.model.Master;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterDAO {
    public List<Master> findAll(Master master);

    public Master findById(Integer id);

    public Master findByName(String name);

    public Master login(Master master);

    public int register(Master master);

    public int update(Master master);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "id") List<Integer> list);
}
