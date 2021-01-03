package com.zanlab.grade.domain;

import java.io.Serializable;

public class Qrcode implements Serializable {
    private Integer id;
    //活动id
    private Integer activityid;
    //qrcodeurl，目前存在七牛云
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
