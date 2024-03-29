package com.zanlab.grade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Judge implements Serializable {
    private Integer id;
    private String name;
    private Integer activityid;
    private Integer userid;

    //不需要json化返回
    //评委活动多对一
    @JsonIgnore
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

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


    @Override
    public String toString() {
        return "Judge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityid=" + activityid +
                ", userid=" + userid +
                ", activity=" + activity +
                '}';
    }
}
