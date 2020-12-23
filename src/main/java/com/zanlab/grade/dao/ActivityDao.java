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
    public List<Activity>findAll();

    @Select("select * from activity where invitationcode=#{code}")
    public Activity findByCode(String code);

    @Select("select *  from activity where id=#{id}")
    public Activity findById(Integer id);

    @Insert("insert into activity (name,sponsor,brief,status,invitationcode,userid,begintime,endtime) " +
            "values(#{name},#{sponsor},#{brief},#{status},#{invitationcode},#{userid},#{begintime},#{endtime})")
    public int save(Activity activity);

    @Update("update activity set name=#{name},sponsor=#{sponsor},brief=#{brief},status=#{status}," +
            "invitationcode=#{invitationcode},userid=#{userid},begintime=#{begintime},endtime={endtime} where id= #{id}" )
    public int update(Activity act);

    @Select("select *  from activity where userid=#{userid}")
    List<Activity> findActivityByUserid(Integer userid);
}
