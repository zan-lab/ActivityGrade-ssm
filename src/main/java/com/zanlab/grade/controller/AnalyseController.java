package com.zanlab.grade.controller;

import com.zanlab.grade.service.ActivityService;
import com.zanlab.grade.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.zanlab.grade.utils.RestfulTool.JsonResult;

@RestController
@RequestMapping(value = "/analyse",produces = "application/json;charset=UTF-8")
public class AnalyseController {
    @Autowired
    ActivityService activityService;
    @Autowired
    AnalyseService analyseService;

    //获取活动的选手排名
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public  String playerOrder(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(analyseService.getPlayerOrder(activityid));
        }
        else return JsonResult(-1,"活动不存在");
    }

    //获取截尾后的选手排名
    @RequestMapping(value = "/fairorder",method = RequestMethod.GET)
    public String playerFairorder(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(analyseService.getPlayerFairOrder(activityid));
        }
        else return JsonResult(-1,"活动不存在");
    }
}
