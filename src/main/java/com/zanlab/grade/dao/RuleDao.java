package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Rule;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleDao {
    @Select("select * from rule where activityid=#{activityId}")
    public List<Rule>findListByActivityid(Integer activityId);

    public int save(Rule rule);

    public Rule findById(Integer id);

    public int update(Rule r);

    public int delete(Integer id);
}
