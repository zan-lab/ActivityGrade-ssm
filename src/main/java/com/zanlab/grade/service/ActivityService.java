package com.zanlab.grade.service;

import com.zanlab.grade.domain.Activity;

import java.util.List;

public interface ActivityService {

    Activity createActivity(Activity activity);

    Activity getActivity(Integer id);

    Boolean hasActivity(Integer activityid);

    Boolean updateActivity(Activity activity);

    Boolean endService(Integer id);

    Boolean isEnd(Integer id);

    List<Activity> getUserAdminActivity(Integer userid);

    List<Activity> getUserActivity(Integer userid);

    Activity getActivityByCode(String code);
}
