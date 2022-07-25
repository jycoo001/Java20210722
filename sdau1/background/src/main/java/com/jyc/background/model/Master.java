package com.jyc.background.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jyc.background.util.MD5Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class Master {
    private Integer id;
    private String name;
    private String password;
    private String picture;
    private String phone;
    private Date loginTime;
    private String status;
    private String remarks;

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

    public String getMiPassword() {
        return MD5Utils.MD5(this.name+"{"+this.password+"}");
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public String getTime() {
        String pattern = "yyyy-MM-dd_HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(loginTime);
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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
}
