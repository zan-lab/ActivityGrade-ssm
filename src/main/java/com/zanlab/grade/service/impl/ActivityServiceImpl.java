package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.ActivityDao;
import com.zanlab.grade.dao.JudgeDao;
import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zanlab.grade.utils.CommonUtils.getRandomString;
import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private JudgeDao judgeDao;

    @Override
    public Activity createActivity(Activity activity) {
        //生成code，并且保证code不重复
        String code=getRandomString(6);
        while(activityDao.findByCode(code)!=null){
            code=getRandomString(6);
        }
        activity.setInvitationcode(code);
        activity.setStatus(1);
        //再根据code来获取这个activity
        if(activityDao.save(activity)==1){
            return activityDao.findByCode(code);
        }
        else return null;
    }

    @Override
    public Activity getActivity(Integer id) {
        return activityDao.findById(id);
    }

    @Override
    public Boolean hasActivity(Integer activityid) {
        return activityDao.findById(activityid)!=null;
    }

    @Override
    public Boolean updateActivity(Activity activity) {
        Activity act=activityDao.findById(activity.getId());
        combineSydwCore(activity,act);
        return activityDao.update(act)==1;
    }

    @Override
    public Boolean endService(Integer id) {
        Activity act= activityDao.findById(id);
        //结束时间定为当前时间
        act.setEndtime(new Date());
        //设置状态码为0，表示已结束
        act.setStatus(0);
        return activityDao.updateWithEndtime(act)==1;
    }

    @Override
    public Boolean isEnd(Integer id) {
        Activity act=activityDao.findById(id);
        return act.getStatus() == 0;
    }

    @Override
    public List<Activity> getUserAdminActivity(Integer userid) {
        return activityDao.findActivityByUserid(userid);
    }

    @Override
    public List<Activity> getUserActivity(Integer userid) {
        List<Activity> res= new ArrayList<>();
        //先根据用户id找评委
        List<Judge> judgeList=judgeDao.findListByUserid(userid);
        //根据评委找到activity
        for(Judge judge : judgeList){
            res.add(judge.getActivity());
        }
        return res;
    }

    @Override
    public Activity getActivityByCode(String code) {
        return activityDao.findByCode(code);
    }
}
