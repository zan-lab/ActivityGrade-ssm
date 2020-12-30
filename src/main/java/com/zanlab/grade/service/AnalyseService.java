package com.zanlab.grade.service;

import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.domain.JudgeStatus;
import com.zanlab.grade.domain.Player;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface AnalyseService {

    //获取选手得分平均值排序
    List<Player> getPlayerOrder(Integer activityid);

    //获取选手得分的截尾均值排序
    List<Player> getPlayerFairOrder(Integer activityid);

    //导出用户-评委得分表并提供下载
    void exportPlayerJudge(Activity act, ServletOutputStream out);

    //导出用户-规则得分表并提供下载
    void exportPlayerRule(Activity act, ServletOutputStream out);

    //导出用户原始得分表并提供下载
    void exportRawData(Activity act, ServletOutputStream out);

    //获取评委打分情况列表
    List<JudgeStatus> getJudgeStatus(Integer activityid);
}
