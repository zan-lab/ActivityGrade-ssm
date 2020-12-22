package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao {
//    @Select("select *,id as playerid from player")
//    @Results(id="gradesMap",value = {
//            @Result(column = "playerid",property = "grades",many = @Many(select = "com.zanlab.grade.dao.GradeDao.findListByActivityid",fetchType = FetchType.LAZY)),
//    })
//    public List<Player>findAll();
    @Select("select * from player where activityid=#{activityId}")
    @ResultMap("gradesMap")
    public List<Player>findListByActivityid(Integer activityId);

    public int save(Player player);

    public Player findById(Integer id);

    public int update(Player p);

    public int delete(Integer id);

    public List<Player> findListByActivityidOrderScore(Integer activityid);

    public List<Player> findListByActivityidOrderByFariScore(Integer activityid);
}
