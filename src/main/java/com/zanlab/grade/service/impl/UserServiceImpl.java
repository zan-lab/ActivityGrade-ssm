package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.UserDao;
import com.zanlab.grade.domain.User;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
