package com.zanlab.grade.dao;

import com.zanlab.grade.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    @Select("select * from user")
    public List<User> findAll();
}
