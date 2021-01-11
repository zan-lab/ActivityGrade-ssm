package com.zanlab.grade.domain;

import java.io.Serializable;

public class Qrcode implements Serializable {
    private Integer id;
    //活动id
    private Integer activityid;
    //qrcodeurl，目前存在七牛云
    private String url;
    //1评委邀请二维码，2正常均值二维码，3截尾均值二维码
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
