package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Judge;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeDao {
    @Select("select * from judge")
    @Results(id = "userMap",value = {
            @Result(property = "user",column = "userid",one = @One(select = "com.zanlab.grade.dao.UserDao.findById",fetchType = FetchType.EAGER))
    })
    public List<Judge> findAll();

    @Select("select * from judge where id=#{activityid}")
    @ResultMap("userMap")
    public List<Judge> findListByActivityid(Integer activityid);

    List<Judge> findListByUseridOrder(Integer userid);

    public int save(Judge judge);

    public Judge findById(Integer judgeid);
}
