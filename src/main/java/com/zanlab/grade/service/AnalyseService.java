package com.zanlab.grade.service;

import com.zanlab.grade.domain.Player;

import java.util.List;

public interface AnalyseService {

    public List<Player> getPlayerOrder(Integer activityid);

    public List<Player> getPlayerFairOrder(Integer activityid);

}
