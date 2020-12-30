package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Activity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {
    @Select("select *  from activity")
    @Results(id = "activityMap",value = {
//            @Result(property = "judges",column = "activityid",many=@Many(select = "com.zanlab.grade.dao.JudgeDao.findListByActivityid",fetchType = FetchType.LAZY)),
//            @Result(property = "user",column = "uid",one=@One(select = "com.zanlab.grade.dao.UserDao.findById",fetchType = FetchType.EAGER)),
//            @Result(property = "players",column = "activityid",many = @Many(select = "com.zanlab.grade.dao.PlayerDao.findListByActivityid",fetchType = FetchType.LAZY)),
//            @Result(property = "grades",column = "activityid",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
//            @Result(property = "rules",column = "activityid",many = @Many(select = "com.zanlab.grade.dao.RuleDao.findListByActivityid",fetchType = FetchType.LAZY)),
    })
    List<Activity>findAll();

    //根据邀请码返回对应个体
    @Select("select * from activity where invitationcode=#{code}")
    Activity findByCode(String code);

    @Select("select *  from activity where id=#{id}")
    Activity findById(Integer id);

    //创建活动时的sql
    @Insert("insert into activity (name,sponsor,brief,status,invitationcode,userid,begintime) " +
            "values(#{name},#{sponsor},#{brief},#{status},#{invitationcode},#{userid},#{begintime})")
    int save(Activity activity);

    //更新活动的sql
    @Update("update activity set name=#{name},sponsor=#{sponsor},brief=#{brief},status=#{status}," +
            "invitationcode=#{invitationcode},userid=#{userid},begintime=#{begintime} where id= #{id}" )
    int update(Activity act);

    //根据userid返回个体列表
    @Select("select *  from activity where userid=#{userid}")
    List<Activity> findActivityByUserid(Integer userid);

    //结束活动时的sql
    @Update("update activity set name=#{name},sponsor=#{sponsor},brief=#{brief},status=#{status}," +
            "invitationcode=#{invitationcode},userid=#{userid},begintime=#{begintime},endtime=#{endtime} where id= #{id}" )
    int updateWithEndtime(Activity act);
}
