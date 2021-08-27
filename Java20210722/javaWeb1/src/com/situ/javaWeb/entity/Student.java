package com.situ.javaWeb.entity;

public class Student {
    private Integer id;
    private String sname;
    private String sex;
    private Integer age;
    private Integer banjiId;

    public Student() {
    }

    public Student(Integer id, String sname, String sex, Integer age) {
        this.id = id;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
    }

    public Student(Integer id, String sname, String sex, Integer age, Integer banjiId) {
        this.id = id;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.banjiId = banjiId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getBanjiId() {
        return banjiId;
    }

    public void setBanjiId(Integer banjiId) {
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
