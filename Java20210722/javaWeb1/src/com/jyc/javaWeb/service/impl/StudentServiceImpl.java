package com.jyc.javaWeb.service.impl;

import com.jyc.javaWeb.dao.StudentDao;
import com.jyc.javaWeb.dao.impl.StudentDaoImpl;
import com.jyc.javaWeb.entity.Student;
import com.jyc.javaWeb.service.StudentService;
import com.jyc.javaWeb.util.PageInfo;
import com.jyc.javaWeb.vo.StudentBanji;

import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public Student selectOne(Integer id) {
        Student student = studentDao.selectOne(id);
        return student;
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public int insert(Student student, int pageSize, int totalCount) {
        studentDao.insert(student);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        studentDao.deleteById(id);
    }

    @Override
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount) {
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        ArrayList<StudentBanji> list = studentDao.selectAll(offset, pageSize);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return studentDao.getCount();
    }
}
