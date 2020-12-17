package com.zanlab.grade.domain;

import java.io.Serializable;

public class Rule implements Serializable {
    private Integer id;
    private String name;
    private Integer activityid;
    private Double fullscore;
    private Double limitScore;
    private Integer status;


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

    public Double getFullscore() {
        return fullscore;
    }

    public void setFullscore(Double fullscore) {
        this.fullscore = fullscore;
    }

    public Double getLimitScore() {
        return limitScore;
    }

    public void setLimitScore(Double limitScore) {
        this.limitScore = limitScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }


    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityid=" + activityid +
                ", fullscore=" + fullscore +
                ", limitScore=" + limitScore +
                ", status=" + status +
                '}';
    }
}
