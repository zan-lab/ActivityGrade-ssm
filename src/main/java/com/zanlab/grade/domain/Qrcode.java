package com.zanlab.grade.domain;

import java.io.Serializable;

public class Qrcode implements Serializable {
    private Integer id;
    private Integer activityd;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityd() {
        return activityd;
    }

    public void setActivityd(Integer activityd) {
        this.activityd = activityd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
