package com.jyc.forward.dao;

import com.jyc.forward.model.Single;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SingleDAO {
    public List<Single> findAll(Single single);

    public List<Single> findByIds(@Param(value = "ids") List<Integer> ids);

    public Integer findCount();
}
