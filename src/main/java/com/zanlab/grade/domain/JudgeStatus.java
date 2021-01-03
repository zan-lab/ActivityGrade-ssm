package com.zanlab.grade.domain;

import java.io.Serializable;

//评委打分情况的平面化数据
public class JudgeStatus implements Serializable {
    private Integer judgeid;
    private String judgename;
    //已经打分数
    private Integer hasjudged;
    //未评数
    private Integer unjudged;
    public JudgeStatus(Judge judge){
        this.judgeid=judge.getId();
        this.judgename=judge.getName();
    }

    public Integer getJudgeid() {
        return judgeid;
    }

    public void setJudgeid(Integer judgeid) {
        this.judgeid = judgeid;
    }

    public String getJudgename() {
        return judgename;
    }

    public void setJudgename(String judgename) {
        this.judgename = judgename;
    }

    public Integer getHasjudged() {
        return hasjudged;
    }

    public void setHasjudged(Integer hasjudged) {
        this.hasjudged = hasjudged;
    }

    public Integer getUnjudged() {
        return unjudged;
    }

    public void setUnjudged(Integer unjudged) {
        this.unjudged = unjudged;
    }
}
