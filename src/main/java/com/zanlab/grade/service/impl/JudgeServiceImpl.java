package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.JudgeDao;
import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("judgeService")
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    private JudgeDao judgeDao;

    @Override
    public List<Judge> getJudgeListByActivityid(Integer activityid) {
        return null;
    }

    @Override
    public Boolean addJudge(Judge judge) {
        return null;
    }

    @Override
    public Boolean judge(Grade grade) {
        return null;
    }

    @Override
    public Boolean hasGrade(Integer id) {
        return null;
    }

    @Override
    public Boolean updateGrade(Grade grade) {
        return null;
    }

    @Override
    public List<Player> getUnjudgedPlayerList(Integer judgeid) {
        return null;
    }

    @Override
    public List<Player> getJudgedPlayerList(Integer judgeid) {
        return null;
    }
}
