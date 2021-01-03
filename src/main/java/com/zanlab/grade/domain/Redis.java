package com.zanlab.grade.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//MySQL模拟Redis
public class Redis implements Serializable {
    //数据库对key关键词进行保护，所以dao层会进行转化
    private Integer id;
    //key,数据库字段是mkey
    private String key;
    //值
    private String value;
    //过期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiretime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    @Override
    public String toString() {
        return "Redis{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", expiretime=" + expiretime +
                '}';
    }
}
