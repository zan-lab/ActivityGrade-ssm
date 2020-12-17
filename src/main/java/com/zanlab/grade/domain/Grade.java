package com.zanlab.grade.domain;

import java.io.Serializable;

public class Grade implements Serializable {
    private Integer id;
    private Integer playerid;
    private Integer judgeid;
    private Integer activityid;
    private Double playerscore;
    private Double rule1;
    private Double rule2;
    private Double rule3;
    private Double rule4;
    private Double rule5;
    private Double rule6;
    private Double rule7;
    private Double rule8;
    private Double rule9;
    private Double rule10;

    private Player player;
    private Judge judge;

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public Integer getJudgeid() {
        return judgeid;
    }

    public void setJudgeid(Integer judgeid) {
        this.judgeid = judgeid;
    }

    public Double getPlayerscore() {
        return playerscore;
    }

    public void setPlayerscore(Double playerscore) {
        this.playerscore = playerscore;
    }

    public Double getRule1() {
        return rule1;
    }

    public void setRule1(Double rule1) {
        this.rule1 = rule1;
    }

    public Double getRule2() {
        return rule2;
    }

    public void setRule2(Double rule2) {
        this.rule2 = rule2;
    }

    public Double getRule3() {
        return rule3;
    }

    public void setRule3(Double rule3) {
        this.rule3 = rule3;
    }

    public Double getRule4() {
        return rule4;
    }

    public void setRule4(Double rule4) {
        this.rule4 = rule4;
    }

    public Double getRule5() {
        return rule5;
    }

    public void setRule5(Double rule5) {
        this.rule5 = rule5;
    }

    public Double getRule6() {
        return rule6;
    }

    public void setRule6(Double rule6) {
        this.rule6 = rule6;
    }

    public Double getRule7() {
        return rule7;
    }

    public void setRule7(Double rule7) {
        this.rule7 = rule7;
    }

    public Double getRule8() {
        return rule8;
    }

    public void setRule8(Double rule8) {
        this.rule8 = rule8;
    }

    public Double getRule9() {
        return rule9;
    }

    public void setRule9(Double rule9) {
        this.rule9 = rule9;
    }

    public Double getRule10() {
        return rule10;
    }

    public void setRule10(Double rule10) {
        this.rule10 = rule10;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", playerid=" + playerid +
                ", judgeid=" + judgeid +
                ", activityid=" + activityid +
                ", playerscore=" + playerscore +
                ", rule1=" + rule1 +
                ", rule2=" + rule2 +
                ", rule3=" + rule3 +
                ", rule4=" + rule4 +
                ", rule5=" + rule5 +
                ", rule6=" + rule6 +
                ", rule7=" + rule7 +
                ", rule8=" + rule8 +
                ", rule9=" + rule9 +
                ", rule10=" + rule10 +
                ", player=" + player +
                ", judge=" + judge +
                '}';
    }
}
