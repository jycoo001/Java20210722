package com.jyc.springboot.dao;

import com.jyc.springboot.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDAO {
    @Select("select id,name,sex,phone,birthday from t_student")
    public List<Student> findAll();
}
