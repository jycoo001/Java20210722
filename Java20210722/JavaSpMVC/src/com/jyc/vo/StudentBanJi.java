package com.jyc.vo;

public class StudentBanJi {
    private Integer id;
    private String sname;
    private String sex;
    private Integer age;
    private String banjiName;

    public StudentBanJi() {
    }

    public StudentBanJi(Integer id, String sname, String sex, Integer age, String banjiName) {
        this.id = id;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.banjiName = banjiName;
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

    public String getBanjiName() {
        return banjiName;
    }

    public void setBanjiName(String banjiName) {
        this.banjiName = banjiName;
    }

    @Override
    public String toString() {
        return "StudentBanJi{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", banjiName='" + banjiName + '\'' +
                '}';
    }
}
