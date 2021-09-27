package com.jyc.javaWeb.service;

import com.jyc.javaWeb.entity.Teacher;
import com.jyc.javaWeb.util.PageInfo;

public interface TeacherService {
    public Teacher selectOne(Integer id);
    public void update(Teacher teacher);
    public int insert(Teacher teacher, int pageSize, int totalCount);
    public void deleteById(Integer id);
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount);
    public int getCount();
}
