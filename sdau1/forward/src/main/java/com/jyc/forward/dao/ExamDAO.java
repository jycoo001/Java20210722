package com.jyc.forward.dao;

import com.jyc.forward.model.Exam;
import com.jyc.forward.model.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamDAO {
    public List<Exam> findAll(Exam exam);

    public Exam findById(Integer id);

    public List<Exam> findByUUId(String uuid);

    public int update(Exam exam);
}
