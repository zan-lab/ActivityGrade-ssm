package com.zanlab.grade.service;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;

import java.util.List;

public interface JudgeService {
    //根据活动id获取活动评委
    List<Judge> getJudgeListByActivityid(Integer activityid);

    //活动添加评委
    Boolean addJudge(Judge judge);

    //评委打分
    Boolean judge(Grade grade);

    //获取指定评委对指定选手的打分
    Grade getGrade(Integer judgeid,Integer playerid);

    //评委是否对指定选手已经打分了
    Boolean hasGrade(Integer judgeid,Integer playerid);

    //更新评委打分的分数
    Boolean updateGrade(Grade grade);

    //获取评委未评分列表
    List<Player> getUnjudgedPlayerList(Integer judgeid);

    //获取评委已打分列表
    List<Player> getJudgedPlayerList(Integer judgeid);

    //获取用户在指定活动的评委信息
    Judge getByUserandActivity(Integer userid, Integer activityid);

    //判断用户在这个活动是否已经有评委注册
    Boolean hasJudge(Integer userid, Integer activityid);

    //判断是否有这个judgeid
    Boolean hasJudge(Integer judgeid);
}