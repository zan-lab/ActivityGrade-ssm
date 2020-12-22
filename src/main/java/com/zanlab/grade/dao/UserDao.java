package com.zanlab.grade.dao;

import com.zanlab.grade.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao {
//    @Select("select * from user")
//    public List<User> findAll();
    @Select("select * from user where id = #{id}")
    public User findById(Integer id);

    @Insert("insert into user (openid,judgename,nickname,avatarurl) values (#{openid},#{judgename},#{nickname},#{avatarurl})")
    public int save(User user);

    @Select("select * from user where openid = #{openid}")
    public User findByOpenid(String openid);

    @Update("update user set openid=#{openid},judgename=#{judgename},nickname=#{nickname},avatarurl=#{avatarurl} where id=#{id}")
    public int update(User user);
}
