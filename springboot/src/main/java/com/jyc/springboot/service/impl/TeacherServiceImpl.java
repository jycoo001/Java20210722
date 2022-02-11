package com.jyc.springboot.service.impl;

import com.jyc.springboot.dao.TeacherDAO;
import com.jyc.springboot.model.Teacher.Teacher;
import com.jyc.springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO dao;

    @Override
    public List<Teacher> findAll() {
        System.out.println("TeacherServiceImpl.findAll");
        return dao.findAll();
    }
}
