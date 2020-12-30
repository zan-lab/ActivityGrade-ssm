package com.zanlab.grade.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(value = {"handler"})
public class Player implements Serializable {
    private Integer id;
    private Integer activityid;
    private String name;
    private String projectname;
    private Double score;
    private Double fairscore;

    //选手得分一对多
    @JsonIgnore
    private List<Grade> grades;
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

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getFairscore() {
        return fairscore;
    }

    public void setFairscore(Double fairscore) {
        this.fairscore = fairscore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", activityid=" + activityid +
                ", name='" + name + '\'' +
                ", projectname='" + projectname + '\'' +
                ", score=" + score +
                ", fairscore=" + fairscore +
                ", grades=" + grades +
                '}';
    }
}
