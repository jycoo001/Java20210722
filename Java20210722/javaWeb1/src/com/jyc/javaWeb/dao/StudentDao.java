package com.jyc.javaWeb.dao;

import com.jyc.javaWeb.entity.Student;
import com.jyc.javaWeb.vo.StudentBanji;

import java.util.ArrayList;

public interface StudentDao {
    public Student selectOne(Integer id);
    public void update(Student student);
    public void insert(Student student);
    public void deleteById(Integer id);
    public ArrayList<StudentBanji> selectAll(int offset, int pageSize);
    public int getCount();
}
