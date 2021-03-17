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
import static com.zanlab.grade.utils.RestfulTool.RetObject;

@RestController
@RequestMapping(value = "/judge",produces = "application/json;charset=UTF-8")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;

    //获取活动的评委列表
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String judgeList(Integer activityid){
        if(activityService.hasActivity(activityid)){
            //关键句
            return JsonResult(judgeService.getJudgeListByActivityid(activityid));
        }
        else {
            return JsonResult(-1,"活动不存在");
        }
    }

    //活动添加评委
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String addJudge(Judge judge){
        if(judge.getUserid()==null)return JsonResult(-2,"用户id缺少");
        else if(judge.getActivityid()==null)return JsonResult(-2,"活动id缺少");
        else{
            if(judgeService.hasJudge(judge.getUserid(),judge.getActivityid())){
                return JsonResult(-4,"用户该活动评委已存在");
            }
            else{
                if(userService.isRegister(judge.getUserid())){
                    if(activityService.hasActivity(judge.getActivityid())){
                        //首先判断是否已经满足上限,满了就直接返回
                        int nowjudgenum=judgeService.getJudgeListByActivityid(judge.getActivityid()).size();
                        int maxjudgenum=activityService.getActivity(judge.getActivityid()).getMaxjudge();
                        if(nowjudgenum>=maxjudgenum){
                            return JsonResult(1,"活动评委已满，请联系活动负责人。");
                        }
                        //关键句
                        if(judgeService.addJudge(judge))return JsonResult();
                        else return JsonResult(-5,"添加失败");
                    }
                    else return JsonResult(-1,"活动不存在");
                }
                else return JsonResult(-1,"用户不存在");
            }
        }
    }

    //查看评委id
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public String getJudgeId(Judge judgePara){
        if(judgePara.getUserid()==null)return JsonResult(-2,"缺少用户id");
        if(judgePara.getActivityid()==null)return JsonResult(-2,"缺少活动id");
        if(userService.isRegister(judgePara.getUserid())){
            if(activityService.hasActivity(judgePara.getActivityid())){
                Judge judge=judgeService.getByUserandActivity(judgePara.getUserid(),judgePara.getActivityid());
                //关键句
                if(judge==null)return JsonResult(RetObject("judgeid",null));
                else return JsonResult(RetObject("judgeid",judge.getId()));
            }
            else return JsonResult(-1,"活动不存在");
        }
        else return JsonResult(-1,"用户不存在");
    }


    //评委评分
    @RequestMapping(value = "/judge",method = RequestMethod.POST)
    public String judge(Grade grade){
        if(grade.getJudgeid()==null)return JsonResult(-2,"评委id缺少");
        else if(grade.getPlayerid()==null)return JsonResult(-2,"选手id缺少");
        else{
            if(!judgeService.hasGrade(grade.getJudgeid(),grade.getPlayerid())){
                //关键句
                if(judgeService.judge(grade))return JsonResult();
                else return JsonResult(-5,"打分失败");
            }
            else return JsonResult(-1,"评委已经打分");
        }
    }

    //查看指定评委对指定选手的打分
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public String judgedetail(Grade grade){
        if(grade.getJudgeid()==null)return JsonResult(-2,"评委id缺少");
        else if(grade.getPlayerid()==null)return JsonResult(-2,"选手id缺少");
        else{
            if(judgeService.hasGrade(grade.getJudgeid(),grade.getPlayerid())){
                //关键句
                return JsonResult(judgeService.getGrade(grade.getJudgeid(),grade.getPlayerid()));
            }
            else return JsonResult(-1,"评委未打分");
        }
    }

    //评委修改评分
    @RequestMapping(value = "/judge",method = RequestMethod.PUT)
    public String rejudge(Grade grade){
        if(grade.getId()==null)return JsonResult(-2,"id缺少");
        else if(grade.getJudgeid()==null)return JsonResult(-2,"评委id缺少");
        else if(grade.getPlayerid()==null)return JsonResult(-2,"选手id缺少");
        else{
            if(judgeService.hasGrade(grade.getJudgeid(),grade.getPlayerid())){
                //关键句
                if(judgeService.updateGrade(grade))return JsonResult();
                else return JsonResult(-5,"修改错误");
            }
            else return JsonResult(-1,"评分不存在");
        }
    }

    //获取未评的列表
    @RequestMapping(value = "/unjudged",method = RequestMethod.GET)
    public String getUnjudgedPlayer(Integer judgeid){
        if(judgeService.hasJudge(judgeid)){
            //关键句
            return JsonResult(judgeService.getUnjudgedPlayerList(judgeid));
        }
        return JsonResult(-1,"评委不存在");
    }

    //获取已经评的列表
    @RequestMapping(value = "/hasjudged",method = RequestMethod.GET)
    public String getJudgedPlayer(Integer judgeid){
        if(judgeService.hasJudge(judgeid)){
            //关键句
            return JsonResult(judgeService.getJudgedPlayerList(judgeid));
        }
        return JsonResult(-1,"评委不存在");
    }
}
