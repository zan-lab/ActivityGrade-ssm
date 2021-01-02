package com.zanlab.grade.service;

import com.zanlab.grade.domain.Redis;

import java.util.Date;

//设置模拟redis，只存key-value且都为string类型
public interface RedisService {
    //判断是否存在
    Boolean exists(String key);
    Boolean del(String key);
    //获取过期时间，秒级
    long ttl(String key);
    //写入键值对，设置什么时候过期
    Redis set(String key, String value, Date expiretime);
    //写入键值对，设置多少秒过期
    Redis set(String key, String value, long seconds);
    //读出键值，过期或者不存在返回null
    String get(String key);

}
