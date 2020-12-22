package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.PlayerDao;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("analyseService")
public class AnalyseServiceImpl implements AnalyseService {

    @Autowired
    private PlayerDao playerDao;
    @Override
    public List<Player> getPlayerOrder(Integer activityid) {
        return playerDao.findListByActivityidOrderScore(activityid);
    }

    @Override
    public List<Player> getPlayerFairOrder(Integer activityid) {
        return playerDao.findListByActivityidOrderByFariScore(activityid);
    }


}
