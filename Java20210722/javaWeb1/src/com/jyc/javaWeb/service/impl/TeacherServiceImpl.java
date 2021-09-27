package com.jyc.javaWeb.service.impl;

import com.jyc.javaWeb.dao.TeacherDao;
import com.jyc.javaWeb.dao.impl.TeacherDaoImpl;
import com.jyc.javaWeb.entity.Teacher;
import com.jyc.javaWeb.service.TeacherService;
import com.jyc.javaWeb.util.PageInfo;

import java.util.ArrayList;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public Teacher selectOne(Integer id) {
        return teacherDao.selectOne(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    public int insert(Teacher teacher, int pageSize, int totalCount) {
        teacherDao.insert(teacher);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        teacherDao.deleteById(id);
    }

    @Override
    public PageInfo selectAll(int pageNumber, int pageSize, int totalCount) {
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        ArrayList<Teacher> list = teacherDao.selectAll(offset, pageSize);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return teacherDao.getCount();
    }
}
