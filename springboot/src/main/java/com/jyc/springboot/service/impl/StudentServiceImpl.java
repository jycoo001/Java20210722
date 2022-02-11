package com.jyc.springboot.service.impl;

import com.jyc.springboot.dao.StudentDAO;
import com.jyc.springboot.model.Student;
import com.jyc.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO dao;

    @Override
    public List<Student> findAl() {
        System.out.println("StudentServiceImpl.findAl");
        return dao.findAll();
    }
}
