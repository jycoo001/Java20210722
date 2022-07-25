package com.jyc.background.dao;

import com.jyc.background.model.Multi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MultiDAO {
    public List<Multi> findAll(Multi multi);

    public List<Multi> findByQuestion(String question);

    public Integer findCount();

    public List<Multi> findByIds(@Param(value = "ids") List<Integer> ids);


    public int insert(Multi multi);

    public int update(Multi multi);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
