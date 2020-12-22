package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao {
    @Select("select *,playerid as pid,judgeid as jid form grade where activityid=#{activityId}")
    @Results(id = "gradeMap",value = {
            @Result(property = "player",column = "pid",one = @One(select = "com.zanlab.grade.dao.PlayerDao.findById",fetchType = FetchType.EAGER)),
            @Result(property = "judge",column = "jid",one = @One(select = "com.zanlab.grade.dao.JudgeDao.findById",fetchType = FetchType.EAGER)),
    })
    public List<Grade>findListByActivityid(Integer activityId);

    @Select("select *,playerid as pid,judgeid as jid form grade where playerid=#{playerid}")
    @ResultMap("gradeMap")
    public List<Grade>findListByPlayerid(Integer playerid);

    @Insert("insert into grade (playerid,judgeid,activityid,playerscore,rule1,rule2,rule3," +
            "rule4,rule5,rule6,rule7,rule8,rule9,rule10) values(#{playerid},#{judgeid},#{activityid},#{playerscore}," +
            "#{rule1},#{rule2},#{rule3},#{rule4},#{rule5},#{rule6},#{rule7},#{rule8},#{rule9},#{rule10})")
    public void save(Grade grade);

    @Select("select *,playerid as pid,judgeid as jid form grade where id=#{id}")
    @ResultMap("gradeMap")
    public Grade findById(Integer id);

    @Update("update grade set playerid=#{playerid},judgeid=#{judgeid},activity=#{activity},playerscore=#{playerscore}," +
            "rule1=#{rule1},rule1=#{rule1},rule1=#{rule2},rule1=#{rule2},rule1=#{rule3},rule1=#{rule3},rule4=#{rule4}" +
            ",rule5=#{rule5},rule6=#{rule6},rule7=#{rule7},rule8=#{rule8},rule9=#{rule9},rule10=#{rule10} where id=#{id}")
    public void update(Grade grade);

    @Select("select *,playerid as pid,judgeid as jid form grade where judgeid=#{judgeid}")
    @ResultMap("gradeMap")
    public List<Player> getListByJudgeid(Integer judgeid);
}
