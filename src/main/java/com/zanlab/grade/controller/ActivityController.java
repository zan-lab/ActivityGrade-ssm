package com.zanlab.grade.controller;

import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.domain.Rule;
import com.zanlab.grade.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.zanlab.grade.utils.RestfulTool.JsonResult;

@RestController
@RequestMapping(value = "/activity",produces = "application/json;charset=UTF-8")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private RuleService ruleService;
    @Autowired
    private PlayerService playerService;

    //创建活动
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String createActivity(Activity activity){
        if(activity.getUserid()==null){
            return JsonResult(-2,"userid缺失");
        }
        Activity activityEntity=activityService.createActivity(activity);
        if(activityEntity==null)return JsonResult(-5,"系统错误");
        else return JsonResult(activityEntity);
    }

    //更新活动基本信息
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public String updateActivity(Activity activity){
        if(activity.getId()==null){
            return JsonResult(-2,"id缺失");
        }
        else if(activity.getUserid()==null){
            return JsonResult(-2,"userid缺失");
        }
        else if(activityService.hasActivity(activity.getId())){
            if(activityService.updateActivity(activity)){
                return JsonResult();
            }
            else return JsonResult(-5,"更新失败");
        }
        else return JsonResult(-1,"活动未找到");
    }

    //获取活动的规则列表
    @RequestMapping(value = "/rule",method = RequestMethod.GET)
    public String rulelist(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(ruleService.getListByActivityid(activityid));
        }
        else return JsonResult(-3,"活动不存在");
    }

    //活动创建规则
    @RequestMapping(value = "/rule",method = RequestMethod.POST)
    public String createRule(Rule rule){
       if(rule.getActivityid()==null)return JsonResult(-2,"activityid缺失");
       else if(activityService.hasActivity(rule.getActivityid()))return JsonResult(-2,"activity不存在");
       else {
           if (ruleService.createRule(rule)) return JsonResult();
           else return JsonResult(-5, "创建失败");
       }
    }

    //更新规则
    @RequestMapping(value = "/rule",method = RequestMethod.PUT)
    public String updateRule(Rule rule){
        if(rule.getId()==null)return JsonResult(-2,"id缺失");
        else if(rule.getActivityid()==null)return JsonResult(-2,"activityid缺失");
        else if(activityService.hasActivity(rule.getActivityid()))return JsonResult(-2,"activity不存在");
        else {
            if (ruleService.updateRule(rule)) return JsonResult();
            else return JsonResult(-5, "修改失败");
        }
    }

    //活动删除规则
    @RequestMapping(value = "/rule/{id}",method = RequestMethod.DELETE)
    public String deleteRule(@PathVariable Integer id){
        if(ruleService.hasRule(id)){
            if(ruleService.deleteRule(id))return JsonResult();
            else return JsonResult(-5,"删除错误");
        }
        return JsonResult(-1,"规则未找到");
    }

    //活动获取选手列表
    @RequestMapping(value = "/player",method = RequestMethod.GET)
    public String playerlist(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(playerService.getListByActivityid(activityid));
        }
        else return JsonResult(-3,"活动不存在");
    }

    //活动创建选手
    @RequestMapping(value = "/player",method = RequestMethod.POST)
    public String createPlayer(Player player){
        if(player.getActivityid()==null)return JsonResult(-2,"activityid缺失");
        else if(activityService.hasActivity(player.getActivityid()))return JsonResult(-2,"activity不存在");
        else {
            if (playerService.createPlayer(player)) return JsonResult();
            else return JsonResult(-5, "创建失败");
        }
    }

    //活动更新选手信息
    @RequestMapping(value = "/player",method = RequestMethod.PUT)
    public String updatePlayer(Player player){
        if(player.getId()==null)return JsonResult(-2,"id缺失");
        else if(player.getActivityid()==null)return JsonResult(-2,"activityid缺失");
        else if(activityService.hasActivity(player.getActivityid()))return JsonResult(-2,"activity不存在");
        else {
            if (playerService.updatePlayer(player)) return JsonResult();
            else return JsonResult(-5, "修改失败");
        }
    }

    //活动删除选手
    @RequestMapping(value = "/player/{id}",method = RequestMethod.DELETE)
    public String deletePlayer(@PathVariable Integer id){
        if(playerService.hasPlayer(id)){
            if(playerService.deletePlayer(id))return JsonResult();
            else return JsonResult(-5,"删除错误");
        }
        return JsonResult(-1,"选手未找到");
    }

    //结束活动
    @RequestMapping(value = "/end",method = RequestMethod.POST)
    public String endActivity(Integer id){
        if(activityService.hasActivity(id)){
            if(!activityService.isEnd(id)){
                if(activityService.endService(id))
                    return JsonResult();
                else return JsonResult(-5,"结束失败");
            }
            else return JsonResult(-4,"比赛已经结束，请勿重复操作");
        }
        else return JsonResult(-1,"比赛未找到");
    }

    //获取活动信息
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getActivity(@PathVariable Integer id){
        return JsonResult(activityService.getActivity(id));
    }

    //获取用户创建的活动列表
    @RequestMapping(value = "/useradmin",method = RequestMethod.GET)
    public String getUserAdminActivity(Integer userid){
        if(userService.isRegister(userid)){
            return JsonResult(userService.isRegister(userid));
        }
        else{
            return JsonResult(activityService.getUserAdminActivity(userid));
        }

    }

    //获得用户参加的活动列表
    @RequestMapping(value = "/userhas",method = RequestMethod.GET)
    public String getUserActivity(Integer userid){
        if(userService.isRegister(userid)){
            return JsonResult(userService.isRegister(userid));
        }
        else{
            return JsonResult(activityService.getUserActivity(userid));
        }

    }

}
