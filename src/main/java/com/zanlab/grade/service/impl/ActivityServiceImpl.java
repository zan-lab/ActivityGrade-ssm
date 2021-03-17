package com.zanlab.grade.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zanlab.grade.dao.ActivityDao;
import com.zanlab.grade.dao.JudgeDao;
import com.zanlab.grade.dao.QrcodeDao;
import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Qrcode;
import com.zanlab.grade.service.ActivityService;
import com.zanlab.grade.service.RedisService;
import com.zanlab.grade.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static com.zanlab.grade.utils.CommonUtils.getRandomString;
import static com.zanlab.grade.utils.Network.uploadWxQrCode;
import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private JudgeDao judgeDao;
    @Autowired
    private WxService wxService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private QrcodeDao qrcodeDao;
    @Override
    public Activity createActivity(Activity activity) {
        //生成code
        String code=getRandomString(6);
        //保证code不重复
        while(activityDao.findByCode(code)!=null){
            code=getRandomString(6);
        }
        activity.setInvitationcode(code);
        //活动起始状态默认为未结束
        activity.setStatus(1);
        //默认最大评委数,默认免费
        activity.setMaxjudge(Integer.parseInt(redisService.get("free_judge")));
        //再根据code来获取这个activity
        if(activityDao.save(activity)==1){
            return activityDao.findByCode(code);
        }
        else return null;
    }

    @Override
    public Activity getActivity(Integer id) {
        return activityDao.findById(id);
    }

    @Override
    public Boolean hasActivity(Integer activityid) {
        return activityDao.findById(activityid)!=null;
    }

    @Override
    public Boolean updateActivity(Activity activity) {
        //先获取数据库的数据
        Activity act=activityDao.findById(activity.getId());
        //进行object合并，把数据库数据填入，避免缺少字段报错
        combineSydwCore(activity,act);
        return activityDao.update(act)==1;
    }

    @Override
    public Boolean endActivity(Integer id) {
        //查询该活动
        Activity act= activityDao.findById(id);
        //结束时间定为当前时间
        act.setEndtime(new Date());
        //设置状态码为0，表示已结束
        act.setStatus(0);
        return activityDao.updateWithEndtime(act)==1;
    }

    @Override
    public Boolean isEnd(Integer id) {
        Activity act=activityDao.findById(id);
        //0代表已经结束了
        return act.getStatus() == 0;
    }

    @Override
    public List<Activity> getUserAdminActivity(Integer userid) {
        return activityDao.findActivityByUserid(userid);
    }

    @Override
    public List<Activity> getUserActivity(Integer userid) {
        List<Activity> res= new ArrayList<>();
        //先根据用户id找评委列表
        List<Judge> judgeList=judgeDao.findListByUserid(userid);
        //根据评委找到activity并插入到返回列表
        for(Judge judge : judgeList){
            res.add(judge.getActivity());
        }
        return res;
    }

    @Override
    public Activity getActivityByCode(String code) {
        return activityDao.findByCode(code);
    }

    @Override
    public String getQRCodeUrl(Integer activityid,Integer type) {
        //id不存在就直接返回null
        if(activityid==null)return null;
        //如果不为空说明有数据，直接返回即可
        Qrcode qc=qrcodeDao.findByActivityidType(activityid,type);
        if(qc!=null)return qc.getUrl();
        //获取redis里的access_token
        String access_token=redisService.get("access_token");
        if(access_token==null){
            try {
                //如果不存在或者过期了需要重新获取
                access_token=wxService.getAccessToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //请求小程序码二进制流
        String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token;
        Map<String, Object> params = new HashMap<>();
        //页面传参
        params.put("scene", "id="+activityid);
        //选择界面
        switch(type){
            case 1:params.put("page", "pages/activity/activity");break;
            case 2:params.put("page", "pages/averagescoreranking/averagescoreranking");break;
            case 3:params.put("page", "pages/averagetruncatedrank/averagetruncatedrank");break;
            default:return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            //object转字符串
            result = mapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        byte[] byteArray = null;
        ResponseEntity<byte[]> entity= restTemplate.postForEntity(url, result,byte[].class);
        byteArray=entity.getBody();

        //***********测试代码******
//        AccessTokenRes entity1= restTemplate.postForObject(url, result,AccessTokenRes.class);
//        System.out.println(entity1);
//        if(true)return null;
        //***********测试代码******

        //传入二进制流、文件名，返回地址
        String p= uploadWxQrCode(byteArray,"qrcode/"+ UUID.randomUUID()+".png");
        //System.out.println(p);
        //地址存入数据库
        Qrcode qrcode=new Qrcode();
        qrcode.setActivityid(activityid);
        qrcode.setUrl(p);
        qrcode.setType(type);
        qrcodeDao.Save(qrcode);
        return p;
    }

}
