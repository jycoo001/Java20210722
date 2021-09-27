package com.jyc.javaWeb.service;

import com.jyc.javaWeb.entity.Student;
import com.jyc.javaWeb.util.PageInfo;

public interface StudentService {
    public Student selectOne(Integer id);
    public void update(Student student);
    public int insert(Student student, int pageSize, int totalCount);
    public void deleteById(Integer id);
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount);
    public int getCount();
}
