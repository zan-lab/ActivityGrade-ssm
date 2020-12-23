package com.zanlab.grade.domain;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String openid;
    private String nickname;
    private String judgename;
    private String avatarurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJudgename() {
        return judgename;
    }

    public void setJudgename(String judgename) {
        this.judgename = judgename;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", judgename='" + judgename + '\'' +
                ", avatarurl='" + avatarurl + '\'' +
                '}';
    }
}
