package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Judge;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeDao {

    //获取活动得评委list
    @Select("select *,activityid as aid from judge where activityid=#{activityid}")
    @Results(id = "judgeMap",value = {
            @Result(property = "activity",column = "aid",one=@One(select = "com.zanlab.grade.dao.ActivityDao.findById",fetchType = FetchType.EAGER))
    })
    List<Judge> findListByActivityid(Integer activityid);

    //获取用户得评委list
    @Select("select *,userid as uid,activityid as aid from judge where userid=#{userid} ")
    @ResultMap("judgeMap")
    List<Judge> findListByUserid(Integer userid);

    //用户进入新比赛时创建评委
    @Update("insert into judge (name,activityid,userid) values(#{name},#{activityid},#{userid})")
    int save(Judge judge);

    @Select("select *,userid as uid,activityid as aid from judge where id=#{id}")
    @ResultMap("judgeMap")
    Judge findById(Integer judgeid);

    //获取指定用户在指定活动得个体
    @Select("select * from judge where userid=#{userid} and activityid=#{activityid}")
    Judge findByUserandActivity(@Param("userid")Integer userid, @Param("activityid")Integer activityid);
}
