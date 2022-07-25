package com.jyc.background.dao;

import com.jyc.background.model.Single;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SingleDAO {
    public List<Single> findAll(Single single);

    public List<Single> findByQuestion(String question);

    public Integer findCount();

    public List<Single> findByIds(@Param(value = "ids") List<Integer> ids);

    public int insert(Single single);

    public int update(Single single);


    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
