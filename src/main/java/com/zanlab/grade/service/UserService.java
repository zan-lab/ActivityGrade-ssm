package com.zanlab.grade.service;

import com.zanlab.grade.domain.User;

public interface UserService {

    //根据id判断用户是否已经注册（是否在user表中）
    Boolean isRegister(Integer userid);

    //用户注册
    Boolean register(User user);

    //根据openid判断用户是否已经注册（是否在user表中）
    Boolean hasOpenid(String openid);

    //更新用户基本信息
    Boolean updateUserInfo(User user);

    //获取用户基本信息（根据id）
    User getUserInfo(Integer userid);

    //根据用户openid获取基本信息

    User getUserInfo(String openid);

    //更新评委名
    Boolean updateJudgename(Integer userid,String judgename);
}
