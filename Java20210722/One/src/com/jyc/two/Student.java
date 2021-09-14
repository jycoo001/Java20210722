package com.jyc.two;

import java.io.Serializable;

public class Student implements Serializable {//对象实现序列化，反序列化! io流IO的IOTest testObj() {}中
    private int id;
    private String name;
    private int age;

    public Student() {
        //super();
        //System.out.println("Student.Student");
    }

    public Student(int id, String name, int age) {

        this.id = id;
        this.name = name;
        if(age>0&&age<=120) {
            this.age = age;
        }else {
            this.age = 0;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if(age>0&&age<=120) {
            this.age = age;
        }else {
            this.age = 0;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
