package com.zanlab.grade.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zanlab.grade.domain.AccessTokenRes;
import com.zanlab.grade.domain.UserLoginRes;
import com.zanlab.grade.service.RedisService;
import com.zanlab.grade.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
@Service("wxService")
public class WxServiceImpl implements WxService {
    @Autowired
    private RedisService redisService;
    private static String appid;
    private static String secret;


    //初始化获取appid和secret
    void init(){
        WxServiceImpl.appid=redisService.get("appid");
        WxServiceImpl.secret=redisService.get("secret");
    }

    //获取最新的token并存进数据库
    @Override
    public String getAccessToken() throws IOException {
        if(appid==null)init();
        String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
        //发送get请求
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper=new ObjectMapper();
        //forObject可以让json转为平板数据
        AccessTokenRes res=mapper.readValue(restTemplate.getForObject(url,String.class), AccessTokenRes.class);
        if(res.getErrcode()==0){
            String token=res.getAccess_token();
            redisService.set("access_token",token,res.getExpires_in());
            return token;
        }
        else return null;
    }

    @Override
    public UserLoginRes code2Session(String code) throws IOException {
        if(appid==null)init();
        //拼接url
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper=new ObjectMapper();
        //发送get请求,forObject可以json转为平板数据
        return mapper.readValue(restTemplate.getForObject(url,String.class), UserLoginRes.class);
    }
}
