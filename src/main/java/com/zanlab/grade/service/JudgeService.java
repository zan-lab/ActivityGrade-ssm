package com.zanlab.grade.service;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;

import java.util.List;

public interface JudgeService {
    List<Judge> getJudgeListByActivityid(Integer activityid);

    public Boolean addJudge(Judge judge);

    public Boolean judge(Grade grade);

    public Boolean hasGrade(Integer id);

    public Boolean updateGrade(Grade grade);

    List<Player> getUnjudgedPlayerList(Integer judgeid);

    List<Player> getJudgedPlayerList(Integer judgeid);
}