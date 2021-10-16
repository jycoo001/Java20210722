package com.jyc.service.impl;

import com.jyc.entity.Teacher;
import com.jyc.mapper.TeacherMapper;
import com.jyc.service.TeacherService;
import com.jyc.util.PageInfo;
import com.jyc.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired

    private TeacherMapper teacherMapper;

    @Override
    public Teacher selectOne(Integer id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherMapper.update(teacher);
    }

    @Override
    public int insert(Teacher teacher, int pageSize) {
        int totalCount = getCount();
        teacherMapper.insert(teacher);
        return (int)Math.ceil((double)totalCount / pageSize);
    }

    @Override
    public void deleteById(Integer id) {
        teacherMapper.deleteById(id);
    }

    @Override
    public PageInfo selectAll(int pageNumber, int pageSize) {
        int totalCount = getCount();
        int offset = (pageNumber-1) * pageSize;
        int totalPage = (int)Math.ceil((double)totalCount / pageSize);
        Page page = new Page(offset, pageSize);
        ArrayList<Teacher> list = teacherMapper.selectAll(page);
        PageInfo pageInfo = new PageInfo(list, pageNumber, totalPage, pageSize);
        return pageInfo;
    }

    @Override
    public int getCount() {
        return teacherMapper.selectTotalCount();
    }
}
