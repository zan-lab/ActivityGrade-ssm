package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Redis;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisDao {
    @Select("select * from redis where mkey=#{Key}")
    @Results(id = "redisMap",value = {
            //@Result(property = "id",column = "id",id = true),
            @Result(column = "mkey",property = "key"),
            //@Result(property = "value",column = "value")
    })
    Redis findByKey(String key);

    @Insert("insert into redis (mkey,value,expiretime) values (#{key},#{value},#{expiretime})")
    int save(Redis redis);

    @Update("update redis set mkey=#{key},value=#{value},expiretime=#{expiretime} where id=#{id}")
    int update(Redis redis);

    @Delete("delete from redis where mkey=#{key}")
    int deleteByKey(String key);
}
