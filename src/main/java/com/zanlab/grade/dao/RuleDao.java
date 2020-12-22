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
    @Select("select * from rule where activityid=#{activityId}")
    public List<Rule>findListByActivityid(Integer activityId);

    @Insert("insert into rule (name,activityid,fullscore,limitscore) values (#{name},#{activityid},#{fullscore},#{limitscore}")
    public int save(Rule rule);

    @Select("select * from rule where id=#{id}")
    public Rule findById(Integer id);

    @Update("update rule set name=#{name},acitivityid=#{activityid},fullscore=#{fullscore},limitscore=#{limitscore} where id=#{id}")
    public int update(Rule r);

    @Delete("delete from rule where id=#{id}")
    public int delete(Integer id);
}
