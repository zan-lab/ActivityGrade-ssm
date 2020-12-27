package com.zanlab.grade.service;

import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.domain.Player;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface AnalyseService {

    List<Player> getPlayerOrder(Integer activityid);

    List<Player> getPlayerFairOrder(Integer activityid);

    void exportPlayerJudge(Activity act, ServletOutputStream out);

    void exportPlayerRule(Activity act, ServletOutputStream out);

    void exportRawData(Activity act, ServletOutputStream out);
}
