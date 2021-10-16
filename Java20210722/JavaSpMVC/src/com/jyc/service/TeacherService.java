package com.jyc.service;

import com.jyc.entity.Teacher;
import com.jyc.util.PageInfo;

public interface TeacherService {
    public Teacher selectOne(Integer id);
    public void update(Teacher teacher);
    public int insert(Teacher teacher, int pageSize);
    public void deleteById(Integer id);
    public PageInfo selectAll(int pageNumber, int pageSize);
    public int getCount();
}
