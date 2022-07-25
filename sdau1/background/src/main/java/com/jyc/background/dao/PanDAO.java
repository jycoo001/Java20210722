package com.jyc.background.dao;

import com.jyc.background.model.Pan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PanDAO {
    public List<Pan> findAll(Pan pan);

    public List<Pan> findByQuestion(String question);

    public Integer findCount();

    public List<Pan> findByIds(@Param(value = "ids") List<Integer> ids);

    public int insert(Pan pan);

    public int update(Pan pan);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
