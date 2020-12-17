package com.zanlab.grade.controller;

import com.zanlab.grade.domain.User;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public void userList(){
        System.out.println("执行了");
        for(User user : userService.findAll()){
            System.out.println(user);
        }
    }
}
