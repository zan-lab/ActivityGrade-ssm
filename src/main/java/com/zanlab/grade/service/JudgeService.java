package com.zanlab.grade.service;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;

import java.util.List;

public interface JudgeService {
    List<Judge> getJudgeListByActivityid(Integer activityid);

    Boolean addJudge(Judge judge);

    Boolean judge(Grade grade);

    Boolean hasGrade(Integer id);

    Boolean updateGrade(Grade grade);

    List<Player> getUnjudgedPlayerList(Integer judgeid);

    List<Player> getJudgedPlayerList(Integer judgeid);

    Judge getByUserandActivity(Integer userid, Integer activityid);

    Boolean hasJudge(Integer userid, Integer activityid);
}