package com.jyc.forward.model;

import com.jyc.forward.util.MD5Utils;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String phone;
    private String password;
    private String picture;
    private Date loginTime;
    private String isExam;
    private Integer violation;
    private String status;
    private String remarks;
    private List<UserPractice> list;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public String getMiPassword() {
        return MD5Utils.MD5(this.name + "{" +this.password + "}");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIsExam() {
        return isExam;
    }

    public void setIsExam(String isExam) {
        this.isExam = isExam;
    }

    public Integer getViolation() {
        return violation;
    }

    public void setViolation(Integer violation) {
        this.violation = violation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<UserPractice> getList() {
        return list;
    }

    public void setList(List<UserPractice> list) {
        this.list = list;
    }

}
