package com.jyc.forward.dao;

import com.jyc.forward.model.Multi;
import com.jyc.forward.model.Single;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MultiDAO {
    public List<Multi> findAll(Multi multi);

    public List<Multi> findByIds(@Param(value = "ids") List<Integer> ids);

    public Integer findCount();
}
