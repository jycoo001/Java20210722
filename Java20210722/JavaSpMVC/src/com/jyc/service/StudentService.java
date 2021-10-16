package com.jyc.service;

import com.jyc.entity.Student;
import com.jyc.util.PageInfo;

public interface StudentService {
    public Student selectOne(Integer id);
    public void update(Student student);
    public int insert(Student student, int pageSize);
    public void deleteById(Integer id);
    public PageInfo selectAll(int pageNumber, int pageSize);
    public int getCount();
}
