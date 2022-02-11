package com.jyc.springboot.service;

import com.jyc.springboot.model.Teacher.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findAll();
}
