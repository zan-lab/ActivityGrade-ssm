package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.RedisDao;
import com.zanlab.grade.domain.Redis;
import com.zanlab.grade.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisDao redisDao;
    @Override
    public Boolean exists(String key) {
        return redisDao.findByKey(key)!=null;
    }

    @Override
    public Boolean del(String key) {
        if(!exists(key))return true;
        return redisDao.deleteByKey(key)==1;
    }

    @Override
    public long ttl(String key) {
        //默认存在该数据
        long expiretimesecond=redisDao.findByKey(key).getExpiretime().getTime();
        long nowtimeseond=new Date().getTime();
        //转化为秒需要除以1000
        return (expiretimesecond-nowtimeseond)/1000;
    }

    @Override
    public Redis set(String key, String value, Date expiretime) {
        //首先判断是否存在
        Redis rs=redisDao.findByKey(key);
        System.out.println(rs);
        if(rs!=null){
            //如果非空，需要把内容填进去并且更新
            rs.setKey(key);
            rs.setValue(value);
            rs.setExpiretime(expiretime);
            redisDao.update(rs);
        }
        else{
            //新建一个Redis
            rs=new Redis();
            rs.setKey(key);
            rs.setValue(value);
            rs.setExpiretime(expiretime);
            redisDao.save(rs);
        }
        //返回redis
        return redisDao.findByKey(key);
    }

    @Override
    public Redis set(String key, String value, long seconds){
        //先获取当前时间的毫秒数
        long nowtimemillisecond=new Date().getTime();
        return set(key,value,new Date(nowtimemillisecond+seconds*1000));
    }

    @Override
    public String get(String key) {
        Redis redis=redisDao.findByKey(key);
        //如果是空则返回null
        if(redis==null)return null;
        else {
            //判断是否过期了,小于10s认为过期了
            if(ttl(key)<10){
                return null;
            }
            else return redis.getValue();
        }
    }
}
