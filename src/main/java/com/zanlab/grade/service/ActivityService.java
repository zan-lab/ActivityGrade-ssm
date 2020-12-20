package com.zanlab.grade.service;

import com.zanlab.grade.domain.Activity;

import java.util.List;

public interface ActivityService {

    public Activity createActivity(Activity activity);

    public Activity getActivity(Integer id);

    public Boolean hasActivity(Integer activityid);

    public Boolean updateActivity(Activity activity);

    public Boolean endService(Integer id);

    public Boolean isEnd(Integer id);

    List<Activity> getUserAdminActivity(Integer userid);

    List<Activity> getUserActivity(Integer userid);
}
