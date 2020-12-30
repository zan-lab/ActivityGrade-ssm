package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Rule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleDao {
    //常见的增删改查和获取列表
    @Select("select * from rule where activityid=#{activityId}")
    List<Rule>findListByActivityid(Integer activityId);

    @Insert("insert into rule (name,activityid,fullscore,limitscore) values (#{name},#{activityid},#{fullscore},#{limitscore})")
    int save(Rule rule);

    @Select("select * from rule where id=#{id}")
    Rule findById(Integer id);

    @Update("update rule set name=#{name},activityid=#{activityid},fullscore=#{fullscore},limitscore=#{limitscore} where id=#{id}")
    int update(Rule r);

    @Delete("delete from rule where id=#{id}")
    int delete(Integer id);
}
