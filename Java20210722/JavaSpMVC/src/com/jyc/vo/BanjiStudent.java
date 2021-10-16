package com.jyc.vo;

import com.jyc.entity.Student;

import java.util.List;

public class BanjiStudent {
    private Integer id;
    private String name;
    private List<Student> list;

    public BanjiStudent() {
    }

    public BanjiStudent(Integer id, String name, List<Student> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BanJiStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
