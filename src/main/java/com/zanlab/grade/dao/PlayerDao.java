package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao {
    @Select("select *,id as playerid from player")
    @Results(id="gradesMap",value = {
            @Result(column = "playerid",property = "grades",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
    })
    public List<Player>findAll();
    @Select("select * from player where activityid=#{activityId}")
    @ResultMap("gradesMap")
    public List<Player>findListByActivityid(Integer activityId);
}
