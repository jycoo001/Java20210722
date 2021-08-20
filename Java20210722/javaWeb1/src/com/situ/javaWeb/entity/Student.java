package com.situ.javaWeb.entity;

public class Student {
    private int id;
    private String sname;
    private String sex;
    private int age;
    private int banjiId;

    public Student() {
        super();
        System.out.println("Student.Student");
    }

    public Student(int id, String sname, String sex, int age) {
        this.id = id;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
    }

    public Student(int id, String sname, String sex, int age, int banjiId) {
        this.id = id;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.banjiId = banjiId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBanjiId() {
        return banjiId;
    }

    public void setBanjiId(int banjiId) {
        this.banjiId = banjiId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", banjiId=" + banjiId +
                '}';
    }
}
