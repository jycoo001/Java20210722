package com.jyc.background.dao;

import com.jyc.background.model.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamDAO {
    public List<Exam> findAll(Exam exam);

    public Exam findById(Integer id);

    public List<Exam> findByUUId(String uuid);

    public int insert(Exam exam);

    public int update(Exam exam);

    public int delete(Integer id);

    public int deleteMany(@Param(value = "ids") List<Integer> ids);
}
