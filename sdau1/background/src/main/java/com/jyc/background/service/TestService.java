package com.jyc.background.service;

import com.jyc.background.model.Single;
import com.jyc.background.model.UserPractice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestService {
    public List<UserPractice> findAll(UserPractice userPractice);

    public UserPractice findById(Integer id);

    public UserPractice findByUUId(String uuid);

    public List<UserPractice> findByUserPractice(String str);

    public List<UserPractice> findByUserId(Integer userId);

    public int insert(UserPractice userPractice);

    public int update(UserPractice userPractice);

    public int delete(Integer id);

    public int deleteMany(Integer[] ids);
}
