package com.zanlab.grade.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable {
    private Integer id;
    private String name;
    private String sponsor;
    private String brief;
    private Integer status;
    private String invitationcode;
    private Integer userid;
    private Date begintime;
    private Date endtime;
    private Date createtime;
    private Date updatetime;
    private User user;
    private List<Player> players;
    private List<Rule> rules;
    private List<Grade> grades;
    private List<Judge>judges;
    public Integer getUserid() {
        return userid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public void setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", brief='" + brief + '\'' +
                ", status=" + status +
                ", invitationcode='" + invitationcode + '\'' +
                ", userid=" + userid +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", user=" + user +
                ", players=" + players +
                ", rules=" + rules +
                ", grades=" + grades +
                ", judges=" + judges +
                '}';
    }
}
