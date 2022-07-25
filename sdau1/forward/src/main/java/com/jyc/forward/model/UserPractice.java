package com.jyc.forward.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserPractice {
    private Integer id;
    private Integer userId;
    private double group;
    private String uuid;
    private Date time;
    private User user;
    private List<Practice> practice;

    public List<Practice> getPractice() {
        return practice;
    }

    public void setPractice(List<Practice> practice) {
        this.practice = practice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getGroup() {
        return group;
    }

    public void setGroup(double group) {
        this.group = group;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFormatTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:ss:mm");
        if(this.getTime()!=null) {
            return sdf.format(this.getTime());
        } else {
            return null;
        }
    }
}
