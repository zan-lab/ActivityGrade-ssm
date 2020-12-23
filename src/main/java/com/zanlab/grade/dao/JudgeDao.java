package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Judge;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeDao {
//    @Select("select * from judge")
//    @Results(id = "userMap",value = {
//            @Result(property = "user",column = "userid",one = @One(select = "com.zanlab.grade.dao.UserDao.findById",fetchType = FetchType.EAGER))
//    })
//    public List<Judge> findAll();

    @Select("select *,activityid as aid from judge where activityid=#{activityid}")
    @Results(id = "judgeMap",value = {
            @Result(property = "activity",column = "aid",one=@One(select = "com.zanlab.grade.dao.ActivityDao.findById",fetchType = FetchType.EAGER))
    })
    List<Judge> findListByActivityid(Integer activityid);

    @Select("select *,userid as uid,activityid as aid from judge where userid=#{userid} ")
    @ResultMap("judgeMap")
    List<Judge> findListByUserid(Integer userid);

    @Update("insert into judge (name,activityid,userid) values(#{name},#{activityid},#{userid})")
    int save(Judge judge);

    @Select("select *,userid as uid,activityid as aid from judge where id=#{id}")
    @ResultMap("judgeMap")
    Judge findById(Integer judgeid);

    @Select("select * from judge where userid=#{userid} and activityid=#{activity})")
    Judge findByUserandActivity(Integer userid, Integer activityid);
}
