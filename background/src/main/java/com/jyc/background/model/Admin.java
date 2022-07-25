package com.jyc.background.model;

public class Admin {
    private Integer id;//管理员id
    private String name;//管理员姓名
    private String password;//管理员密码
    private String phone;
    private Integer rollId;//权限id

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRollId() {
        return rollId;
    }

    public void setRollId(Integer rollId) {
        this.rollId = rollId;
    }
}
