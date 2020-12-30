package com.zanlab.grade.service;

import com.zanlab.grade.domain.Activity;

import java.util.List;

public interface ActivityService {

    //创建活动
    Activity createActivity(Activity activity);

    //获取活动
    Activity getActivity(Integer id);

    //活动是否存在
    Boolean hasActivity(Integer activityid);

    //更新活动
    Boolean updateActivity(Activity activity);

    //结束活动
    Boolean endActivity(Integer id);

    //活动是否结束
    Boolean isEnd(Integer id);

    //获取用户管理的活动
    List<Activity> getUserAdminActivity(Integer userid);

    //获取用户参与的活动
    List<Activity> getUserActivity(Integer userid);

    //通过邀请码获取活动
    Activity getActivityByCode(String code);
}
