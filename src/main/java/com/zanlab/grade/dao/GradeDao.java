package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Grade;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao {
    //获取活动的grade list
    @Select("select *,judgeid as jid from grade where activityid=#{activityId}")
    @Results(id = "gradeMap",value = {
            //@Result(property = "player",column = "pid",one = @One(select = "com.zanlab.grade.dao.PlayerDao.findById",fetchType = FetchType.EAGER)),
            @Result(property = "judge",column = "jid",one = @One(select = "com.zanlab.grade.dao.JudgeDao.findById",fetchType = FetchType.EAGER)),
    })
    List<Grade>findListByActivityid(Integer activityId);

    //获取选手的得分list的sql
    @Select("select *,judgeid as jid from grade where playerid=#{playerid}")
    @ResultMap("gradeMap")
    List<Grade>findListByPlayerid(Integer playerid);

    //打分时得sql
    @Insert("insert into grade (playerid,judgeid,activityid,playerscore,rule1,rule2,rule3," +
            "rule4,rule5,rule6,rule7,rule8,rule9,rule10) values(#{playerid},#{judgeid},#{activityid},#{playerscore}," +
            "#{rule1},#{rule2},#{rule3},#{rule4},#{rule5},#{rule6},#{rule7},#{rule8},#{rule9},#{rule10})")
    void save(Grade grade);

    @Select("select *,judgeid as jid from grade where id=#{id}")
    @ResultMap("gradeMap")
    Grade findById(Integer id);

    //更新打分
    @Update("update grade set playerid=#{playerid},judgeid=#{judgeid},activityid=#{activityid},playerscore=#{playerscore}," +
            "rule1=#{rule1},rule2=#{rule2},rule3=#{rule3},rule4=#{rule4},rule5=#{rule5},rule6=#{rule6},rule7=#{rule7}" +
            ",rule8=#{rule8},rule9=#{rule9},rule10=#{rule10} where id=#{id}")
    void update(Grade grade);

    //获取评分grade list
    @Select("select *,judgeid as jid from grade where judgeid=#{judgeid}")
    @ResultMap("gradeMap")
    List<Grade> getListByJudgeid(Integer judgeid);

    //获取特定评委对特定选手的打分个体
    @Select("select * from grade where judgeid=#{judgeid} and playerid=#{playerid}")
    Grade findByJidandPid(@Param("judgeid")Integer judgeid, @Param("playerid") Integer playerid);
}
