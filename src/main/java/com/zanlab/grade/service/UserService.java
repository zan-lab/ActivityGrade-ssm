package com.zanlab.grade.service;

import com.zanlab.grade.domain.User;

public interface UserService {

    public Boolean isRegister(Integer userid);

    public Boolean register(User user);

    public Boolean hasOpenid(String openid);

    public Boolean updateUserInfo(User user);

    public User getUserInfo(Integer userid);

    public User getUserInfo(String openid);

    public Boolean updateJudgename(Integer userid,String judgename);
}
