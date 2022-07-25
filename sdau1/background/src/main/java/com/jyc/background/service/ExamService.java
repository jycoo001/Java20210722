package com.jyc.background.service;

import com.jyc.background.model.ExamSet;
import com.jyc.background.model.UserExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamService {
    public List<UserExam> findAll(UserExam userExam);

    public UserExam findById(Integer id);

    public UserExam findByUUId(String uuid);

    public List<UserExam> findByUserExam(String str);

    public List<UserExam> findByUserId(Integer userId);

    public int insert(ExamSet examSet);

    public int update(UserExam userExam);

    public int delete(Integer id);

    public int deleteMany(Integer[] ids);

    public List<UserExam> findByIds(Integer[] ids);
}
