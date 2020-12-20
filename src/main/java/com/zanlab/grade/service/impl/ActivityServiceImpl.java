package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.ActivityDao;
import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Override
    public Activity createActivity(Activity activity) {
        return null;
    }

    @Override
    public Activity getActivity(Integer id) {
        return null;
    }

    @Override
    public Boolean hasActivity(Integer activityid) {
        return null;
    }

    @Override
    public Boolean updateActivity(Activity activity) {
        return null;
    }

    @Override
    public Boolean endService(Integer id) {
        return null;
    }

    @Override
    public Boolean isEnd(Integer id) {
        return null;
    }

    @Override
    public List<Activity> getUserAdminActivity(Integer userid) {
        return null;
    }

    @Override
    public List<Activity> getUserActivity(Integer userid) {
        return null;
    }
}
