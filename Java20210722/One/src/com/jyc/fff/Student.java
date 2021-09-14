package com.jyc.fff;

import java.io.Serializable;

public class Student implements Serializable {//对象实现序列化，反序列化! io流IO的IOTest testObj() {}中
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
