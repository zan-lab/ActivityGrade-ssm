package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao {

    //获取活动选手list
    @Select("select *,activityid as aid from player where activityid=#{activityId}")
    @Results(id="playerMap",value = {
            @Result(property = "grades",column = "aid",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
    })
    List<Player>findListByActivityid(Integer activityId);

    //添加选手sql
    @Insert("insert into player (activityid,name,projectname) values(#{activityid},#{name},#{projectname})")
    int save(Player player);

    @Select("select *,activityid as aid from player where id=#{id}")
    @ResultMap("playerMap")
    Player findById(Integer id);

    @Update("update player set activityid=#{activityid},name=#{name},projectname=#{projectname} where id=#{id}")
    int update(Player p);

    //评委打分时，对player表同步进行更新
    @Update("update player set activityid=#{activityid},name=#{name},projectname=#{projectname},score=#{score},fairscore=#{fairscore} where id=#{id}")
    void updateWithScore(Player p);

    @Delete("delete from player where id=#{id}")
    int delete(Integer id);

    //获取某个活动根据Score列进行排序的结果
    @Select("select *,activityid as aid from player where activityid=#{activityid} order by score desc")
    @ResultMap("playerMap")
    List<Player> findListByActivityidOrderScore(Integer activityid);

    //获取某个活动根据fairscore列进行排序的结果
    @Select("select *,activityid as aid from player where activityid=#{activityid} order by fairscore desc")
    @ResultMap("playerMap")
    List<Player> findListByActivityidOrderByFariScore(Integer activityid);


}
