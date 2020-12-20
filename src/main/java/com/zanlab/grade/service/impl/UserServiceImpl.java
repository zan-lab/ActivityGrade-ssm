package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.UserDao;
import com.zanlab.grade.domain.User;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean isRegister(Integer userid) {
        return userDao.findById(userid) != null;
    }

    @Override
    public Boolean register(User user) {
        return null;
    }

    @Override
    public Boolean hasOpenid(String openid) {
        return null;
    }

    @Override
    public Boolean updateUserInfo(User user) {
        return null;
    }

    @Override
    public User getUserInfo(Integer userid) {
        return null;
    }

    @Override
    public User getUserInfo(String openid) {
        return null;
    }

    @Override
    public Boolean updateJudgename(Integer userid, String judgename) {
        return null;
    }
}
