package com.zanlab.grade.dao;

import com.zanlab.grade.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao {
    //常见的增删改查
    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Insert("insert into user (openid,judgename,nickname,avatarurl) values (#{openid},#{judgename},#{nickname},#{avatarurl})")
    int save(User user);

    @Select("select * from user where openid = #{openid}")
    User findByOpenid(String openid);

    @Update("update user set openid=#{openid},judgename=#{judgename},nickname=#{nickname},avatarurl=#{avatarurl} where id=#{id}")
    int update(User user);
}
