package com.jyc.forward.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserExam {
    private Integer id;
    private Integer userId;
    private Date startTime;
    private Date lastTime;
    private Double group;
    private String uuid;
    private String isDoing;
    private List<Exam> list;
    private User user;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Double getGroup() {
        return group;
    }

    public void setGroup(Double group) {
        this.group = group;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Exam> getList() {
        return list;
    }

    public void setList(List<Exam> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIsDoing() {
        return isDoing;
    }

    public void setIsDoing(String isDoing) {
        this.isDoing = isDoing;
    }

    public String getFormatStart() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm;ss");
        if(this.getStartTime()!=null) {
            return simpleDateFormat.format(this.getStartTime());
        } else {
            return null;
        }
    }

    public String getFormatLast() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm;ss");
        if(this.getLastTime()!=null) {
            return simpleDateFormat.format(this.getLastTime());
        } else {
            return null;
        }
    }
}
