package com.jyc.javaWeb.dao;

import com.jyc.javaWeb.entity.Teacher;

import java.util.ArrayList;

public interface TeacherDao {
    public Teacher selectOne(Integer id);
    public void update(Teacher teacher);
    public void insert(Teacher teacher);
    public void deleteById(Integer id);
    public ArrayList<Teacher> selectAll(int offset, int pageSize);
    public int getCount();
}
