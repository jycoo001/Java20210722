package com.jyc.springboot.dao;

import com.jyc.springboot.model.Teacher.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherDAO {
    @Select("select id,employee_id,name,gender from t_teacher")
    public List<Teacher> findAll();
}
