package com.zanlab.grade.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public UserLoginRes code2Session(String code) throws IOException {
        if(appid==null)init();
        //拼接url
        System.out.println(appid);
        System.out.println(secret);
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        //json转object
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper=new ObjectMapper();
        //json转为平板数据
        return mapper.readValue(restTemplate.getForObject(url,String.class), UserLoginRes.class);
    }
}
