package com.zanlab.grade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zanlab.grade.domain.User;
import com.zanlab.grade.domain.UserLoginRes;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.zanlab.grade.utils.RestfulTool.JsonResult;
import static com.zanlab.grade.utils.RestfulTool.RetObject;

@RestController
@RequestMapping(value="/user",produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    //用户微信授权登录，根据code获取用户openid
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(String code) throws IOException{
        String appid="wx69dd67d279607eec";
        String secret="4b418749ed68414cea07b8ca7f3da6bc";
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper=new ObjectMapper();
        UserLoginRes res = mapper.readValue(restTemplate.getForObject(url,String.class), UserLoginRes.class);
        if(res.getOpenid()==null){
            return JsonResult(-5,res.getErrmsg());
        }
        else{
            return JsonResult(RetObject("openid",res.getOpenid()));
        }
    }

    //用户注册
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String register(User user){
        if(user.getOpenid()==null){
            return JsonResult(-2,"缺少openid");
        }
        else if(user.getAvataurl()==null){
            return JsonResult(-2,"缺少头像地址");
        }
        else if(user.getNickname()==null){
            return JsonResult(-2,"缺少头像地址");
        }
        else{
            if(userService.hasOpenid(user.getOpenid())){
                return JsonResult(-4,"用户已注册");
            }
            else{
                if(userService.register(user))return getUserByOpenid(user.getOpenid());
                else return JsonResult(-5,"添加失败");
            }
        }
    }

    //用户信息更新
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public String update(User user){
        if(user.getOpenid()==null){
            return JsonResult(-2,"缺少openid");
        }
        else if(user.getAvataurl()==null){
            return JsonResult(-2,"缺少头像地址");
        }
        else if(user.getNickname()==null){
            return JsonResult(-2,"缺少头像地址");
        }
        else{
            if(userService.isRegister(user.getId())==null){
                return JsonResult(-3,"未查到此用户");
            }
            else{
                if(userService.updateUserInfo(user))return JsonResult();
                else return JsonResult(-5,"更新失败");
            }
        }
    }

    //用户信息获取
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getUserById(Integer id){
     if(userService.isRegister(id)){
         return JsonResult(userService.getUserInfo(id));
     }
     else return  JsonResult(-3,"未查到此用户");
    }

    //用户信息获取（根据openid）
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getUserByOpenid(String openid){
        if(userService.hasOpenid(openid)){
            return JsonResult(userService.getUserInfo(openid));
        }
        else return  JsonResult(-3,"未查到此用户");
    }

    //用户常用评委名修改
    @RequestMapping(value = "/judgename",method = RequestMethod.PUT)
    public String updateJudgename(Integer id,String judgename){
        if(userService.isRegister(id)){
            if(userService.updateJudgename(id,judgename))return JsonResult();
            else return JsonResult(-5,"修改失败");
        }
        else return  JsonResult(-3,"未查到此用户");
    }


}
