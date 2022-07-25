package com.jyc.background.dao;

import com.jyc.background.model.Practice;
import com.jyc.background.model.Single;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PracticeDAO {
    public List<Practice> findAll(Practice practice);

    public Practice findById(Integer id);

    public List<Practice> findByUUId(String uuid);

    public int insert(Practice userPractice);

    public int update(Practice userPractice);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
