package com.zanlab.grade.domain;

import java.io.Serializable;

public class Judge implements Serializable {
    private Integer id;
    private String name;
    private Integer activityid;
    private Integer userid;

    private User user;

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

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Judge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityid=" + activityid +
                ", userid=" + userid +
                ", user=" + user +
                '}';
    }
}
