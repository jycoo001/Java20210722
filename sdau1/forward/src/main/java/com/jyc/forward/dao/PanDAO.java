package com.jyc.forward.dao;

import com.jyc.forward.model.Multi;
import com.jyc.forward.model.Pan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PanDAO {
    public List<Pan> findAll(Pan pan);

    public List<Pan> findByIds(@Param(value = "ids") List<Integer> ids);

    public Integer findCount();
}
