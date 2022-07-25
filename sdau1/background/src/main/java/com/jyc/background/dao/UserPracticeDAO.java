package com.jyc.background.dao;

import com.jyc.background.model.Single;
import com.jyc.background.model.UserPractice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPracticeDAO {
    public List<UserPractice> findAll(UserPractice userPractice);

    public UserPractice findById(Integer id);

    public UserPractice findByUUId(String uuid);

    public List<UserPractice> findByUserPractice(String str);

    public List<UserPractice> findByUserId(Integer userId);

    public int insert(UserPractice userPractice);

    public int update(UserPractice userPractice);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);

    public List<UserPractice> findByIds(@Param(value = "ids") List<Integer> ids);

}
