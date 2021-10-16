package com.jyc.mapper;

import com.jyc.entity.Student;
import com.jyc.vo.Page;
import com.jyc.vo.StudentBanji;

import java.util.ArrayList;

public interface StudentMapper {
    public Student selectById(Integer id);
    public void update(Student student);
    public void insert(Student student);
    public void deleteById(Integer id);
    public ArrayList<StudentBanji> selectAll(Page page);
    public int selectTotalCount();
}
