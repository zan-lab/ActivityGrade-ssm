package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao {
//    @Select("select *,id as playerid from player")
//    @Results(id="gradesMap",value = {
//            @Result(column = "playerid",property = "grades",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
//    })
//     List<Player>findAll();
    @Select("select *,activityid as aid from player where activityid=#{activityId}")
    @Results(id="playerMap",value = {
            @Result(property = "grades",column = "aid",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
    })
    List<Player>findListByActivityid(Integer activityId);

    @Insert("insert into player (activityid,name,projectname) values(#{activityid},#{name},#{projectname})")
    int save(Player player);

    @Select("select *,activityid as aid from player where id=#{id}")
    @ResultMap("playerMap")
    Player findById(Integer id);

    @Update("update player set activityid=#{activityid},name=#{name},projectname=#{projectname} where id=#{id}")
    int update(Player p);

    @Delete("delete from player where id=#{id}")
    int delete(Integer id);

    @Select("select *,activityid as aid from player where activityid=#{activityid} order by score desc")
    @ResultMap("playerMap")
    List<Player> findListByActivityidOrderScore(Integer activityid);

    @Select("select *,activityid as aid from player where activityid=#{activityid} order by fairscore desc")
    @ResultMap("playerMap")
    List<Player> findListByActivityidOrderByFariScore(Integer activityid);
}
