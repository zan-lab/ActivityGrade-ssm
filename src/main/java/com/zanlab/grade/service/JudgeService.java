package com.zanlab.grade.service;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;

import java.util.List;

public interface JudgeService {
    List<Judge> getJudgeListByActivityid(Integer activityid);

    Boolean addJudge(Judge judge);

    Boolean judge(Grade grade);

    Boolean hasGrade(Integer judgeid,Integer playerid);

    Boolean updateGrade(Grade grade);

    List<Player> getUnjudgedPlayerList(Integer judgeid);

    List<Player> getJudgedPlayerList(Integer judgeid);

    Judge getByUserandActivity(Integer userid, Integer activityid);

    //判断用户在这个活动是否已经有评委注册
    Boolean hasJudge(Integer userid, Integer activityid);

    //判断是否有这个judgeid
    Boolean hasJudge(Integer judgeid);
}