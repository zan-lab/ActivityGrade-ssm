package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.UserDao;
import com.zanlab.grade.domain.User;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

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
        //注册时默认把评委名置空，避免sql报错
        user.setJudgename("");
        return userDao.save(user) == 1;
    }

    @Override
    public Boolean hasOpenid(String openid) {
        return userDao.findByOpenid(openid)!=null;
    }

    @Override
    public Boolean updateUserInfo(User user) {
        //查询已有数据
       User u=userDao.findById(user.getId());
       //将user的内容合并到数据库查到的数据中，避免dao层报错
       combineSydwCore(user,u);
       //更新user，返回影响的行数为1代表更新成功，否则更新失败
       return userDao.update(u)==1;
    }

    @Override
    public User getUserInfo(Integer userid) {
        return userDao.findById(userid);
    }

    @Override
    public User getUserInfo(String openid) {
        return userDao.findByOpenid(openid);
    }

    @Override
    public Boolean updateJudgename(Integer userid, String judgename) {
        //获取已有数据
        User u=userDao.findById(userid);
        //更新judgename字段
        u.setJudgename(judgename);
        return userDao.update(u)==1;
    }
}
