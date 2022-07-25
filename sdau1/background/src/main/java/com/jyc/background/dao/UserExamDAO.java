package com.jyc.background.dao;

import com.jyc.background.model.ExamSet;
import com.jyc.background.model.UserExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserExamDAO {
    public List<UserExam> findAll(UserExam userExam);

    public UserExam findById(Integer id);

    public UserExam findByUUId(String uuid);

    public List<UserExam> findByUserExam(String str);

    public List<UserExam> findByUserId(Integer userId);

    public List<UserExam> findByUserIds(@Param(value = "ids") List<Integer> ids);

    public int insert(UserExam userExam);

    public int update(UserExam userExam);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);

    public List<UserExam> findByIds(@Param(value = "ids") List<Integer> ids);

}
