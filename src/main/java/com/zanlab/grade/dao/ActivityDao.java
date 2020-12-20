package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {
    @Select("select *,id as activityid,userid as uid  from activity")
    @Results(value = {
            @Result(column = "activityid",property = "judges",many=@Many(select = "com.zanlab.grade.dao.JudgeDao.findListByActivityid",fetchType = FetchType.LAZY)),
            @Result(column = "uid",property = "user",one=@One(select = "com.zanlab.grade.dao.UserDao.findById",fetchType = FetchType.LAZY)),
            @Result(column = "activityid",property = "players",many = @Many(select = "com.zanlab.grade.dao.PlayerDao.findListByActivityid",fetchType = FetchType.LAZY)),
            @Result(column = "activityid",property = "grades",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
            @Result(column = "activityid",property = "rules",many = @Many(select = "com.zanlab.grade.dao.RuleDao.findListByActivityid",fetchType = FetchType.LAZY)),
    })
    public List<Activity>findAll();
}
