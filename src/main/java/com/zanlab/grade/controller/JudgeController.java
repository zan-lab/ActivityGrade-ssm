package com.zanlab.grade.controller;

import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.service.ActivityService;
import com.zanlab.grade.service.JudgeService;
import com.zanlab.grade.service.PlayerService;
import com.zanlab.grade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.zanlab.grade.utils.RestfulTool.JsonResult;

@RestController
@RequestMapping(value = "/Judge",produces = "application/json;charset=UTF-8")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String judgeList(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(judgeService.getJudgeListByActivityid(activityid));
        }
        else {
            return JsonResult(-1,"活动不存在");
        }
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String addJudge(Judge judge){
        if(judge.getUserid()==null)return JsonResult(-2,"用户id缺少");
        else if(judge.getActivityid()==null)return JsonResult(-2,"活动id缺少");
        else{
            if(userService.isRegister(judge.getUserid())){
             if(activityService.hasActivity(judge.getActivityid())){
                if(judgeService.addJudge(judge))return JsonResult();
                else return JsonResult(-5,"添加失败");
             }
             else return JsonResult(-1,"活动不存在");
            }
            else return JsonResult(-1,"用户不存在");
        }
    }
    @RequestMapping(value = "/judge",method = RequestMethod.POST)
    public String judge(Grade grade){
        if(grade.getId()==null)return JsonResult(-2,"评分id缺少");
        else if(grade.getActivityid()==null)return JsonResult(-2,"活动id缺少");
        else if(grade.getPlayerid()==null)return JsonResult(-2,"选手id缺少");
        else{
            if(judgeService.hasGrade(grade.getId())){
                if(judgeService.updateGrade(grade))return JsonResult();
                else return JsonResult(-5,"修改错误");
            }
            else return JsonResult(-1,"评分不存在");
        }
    }
    @RequestMapping(value = "/unjudged",method = RequestMethod.GET)
    public String getUnjudgedPlayer(Integer judgeid){
        if(judgeService.hasGrade(judgeid)){
            return JsonResult(judgeService.getUnjudgedPlayerList(judgeid));
        }
        return JsonResult(-1,"评委不存在");
    }
    @RequestMapping(value = "/hasjudged",method = RequestMethod.GET)
    public String getJudgedPlayer(Integer judgeid){
        if(judgeService.hasGrade(judgeid)){
            return JsonResult(judgeService.getJudgedPlayerList(judgeid));
        }
        return JsonResult(-1,"评委不存在");
    }
}
