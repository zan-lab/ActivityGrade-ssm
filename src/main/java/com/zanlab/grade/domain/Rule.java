package com.zanlab.grade.domain;

import java.io.Serializable;

public class Rule implements Serializable {
    private Integer id;
    private String name;
    private Integer activityid;
    private Double fullscore;
    private Double limitscore;


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

    public Double getLimitscore() {
        return limitscore;
    }

    public void setLimitscore(Double limitscore) {
        this.limitscore = limitscore;
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
                ", limitScore=" + limitscore +
                '}';
    }
}
