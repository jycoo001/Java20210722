package com.jyc.mapper;

import com.jyc.entity.Teacher;
import com.jyc.vo.Page;

import java.util.ArrayList;

public interface TeacherMapper {
    public Teacher selectById(Integer id);
    public void update(Teacher teacher);
    public void insert(Teacher teacher);
    public void deleteById(Integer id);
    public ArrayList<Teacher> selectAll(Page page);
    public int selectTotalCount();
}
