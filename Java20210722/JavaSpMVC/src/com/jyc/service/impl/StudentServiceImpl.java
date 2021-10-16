package com.jyc.service.impl;

import com.jyc.entity.Student;
import com.jyc.mapper.StudentMapper;
import com.jyc.service.StudentService;
import com.jyc.util.PageInfo;
import com.jyc.vo.Page;
import com.jyc.vo.StudentBanji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student selectOne(Integer id) {
        Student student = studentMapper.selectById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public int insert(Student student, int pageSize) {
        int totalCount = getCount();
        studentMapper.insert(student);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public PageInfo selectAll(int pageNumber, int pageSize) {
        int totalCount = getCount();
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        Page page = new Page(offset, pageSize);
        ArrayList<StudentBanji> list = studentMapper.selectAll(page);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return studentMapper.selectTotalCount();
    }

}
