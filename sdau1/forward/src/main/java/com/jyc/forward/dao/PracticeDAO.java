package com.jyc.forward.dao;

import com.jyc.forward.model.Practice;
import com.jyc.forward.model.Single;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PracticeDAO {
    public List<Practice> findAll(Single single);

    public Practice findById(Integer id);

    public List<Practice> findByUUId(String uuid);

    public int insert(Practice userPractice);

    public int update(Practice userPractice);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
