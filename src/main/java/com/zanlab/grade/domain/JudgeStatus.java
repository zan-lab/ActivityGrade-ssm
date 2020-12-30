package com.zanlab.grade.domain;

import java.io.Serializable;

public class JudgeStatus implements Serializable {
    private Integer judgeid;
    private String judgename;
    private Integer hasjudged;
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
