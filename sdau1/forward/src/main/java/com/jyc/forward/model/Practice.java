package com.jyc.forward.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Practice {
    private Integer id;
    private String uuid;
    private Integer questionId;
    private String type;
    private String right;
    private Date time;
    private String userSelect;
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

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserSelect() {
        return userSelect;
    }

    public void setUserSelect(String userSelect) {
        this.userSelect = userSelect;
    }

    public Double getGroup() {
        return group;
    }

    public void setGroup(Double group) {
        this.group = group;
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
