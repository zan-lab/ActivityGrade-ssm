package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Player;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao {
    @Select("select * form grade where activityid=#{activityId}")
    @Results()
    public List<Grade>findListByActivityid(Integer activityId);
    public List<Grade>findListByPlayerid(Integer activityId);

    public void save(Grade grade);

    public Grade findById(Integer id);

    public void update(Grade grade);

    public List<Player> getListByJudgeid(Integer judgeid);
}
