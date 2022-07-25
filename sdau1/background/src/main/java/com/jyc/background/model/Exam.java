package com.jyc.background.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
    private Integer id;
    private String uuid;
    private String userSelect;
    private String right;
    private Integer questionId;
    private String type;
    private Date time;
    private Double group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserSelect() {
        return userSelect;
    }

    public void setUserSelect(String userSelect) {
        this.userSelect = userSelect;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getGroup() {
        return group;
    }

    public void setGroup(Double group) {
        this.group = group;
    }

    public String getFormatTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        if(this.getTime()!=null) {
            return simpleDateFormat.format(this.getTime());
        } else {
            return null;
        }
    }
}
