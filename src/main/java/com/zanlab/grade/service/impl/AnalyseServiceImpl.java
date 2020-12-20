package com.zanlab.grade.service.impl;

import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.AnalyseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("analyseService")
public class AnalyseServiceImpl implements AnalyseService {
    @Override
    public List<Player> getPlayerOrder(Integer activityid) {
        return null;
    }

    @Override
    public List<Player> getPlayerFairOrder(Integer activityid) {
        return null;
    }
}
