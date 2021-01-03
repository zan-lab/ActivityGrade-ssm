package com.zanlab.grade.controller;

import com.zanlab.grade.domain.Activity;
import com.zanlab.grade.service.ActivityService;
import com.zanlab.grade.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    //获取评委打分情况
    @RequestMapping(value = "/judgestatus",method = RequestMethod.GET)
    public String getJudgeStatus(Integer activityid){
        if(activityService.hasActivity(activityid)){
            return JsonResult(analyseService.getJudgeStatus(activityid));
        }
        else return JsonResult(-1,"活动不存在");
    }

    //获取选手-评委表
    @RequestMapping(value = "/excel/playerjudge",method = RequestMethod.GET)
    public String downPlayerJudge(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        //需要response，用来返回文件流
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        //获取当前活动activity
        Activity act=activityService.getActivity(activityid);
        //设置返回格式,注意微信需要这样设置contenttype
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        try{
            //打开输出流
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_选手得分表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
                return JsonResult(-5,"系统错误");
            }
            //导出表并返回下载流
            analyseService.exportPlayerJudge(act,out);
            //如果正确导出不会执行return
            return JsonResult(-5,"正常执行返回");
        } catch(Exception e){
            e.printStackTrace();
            //报错应该给前端一个返回
            return JsonResult(-5,"系统错误");
        }
    }

    //获取选手详细得分表
    @RequestMapping(value = "/excel/playerrule",method = RequestMethod.GET)
    public String downPlayerRule(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        Activity act=activityService.getActivity(activityid);
        //设置返回格式,注意微信需要这样设置contenttype
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        try{
            //打开输出流
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_选手详细得分表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                //报错应该给前端一个返回
                return JsonResult(-5,"系统错误");
            }
            //导出表并返回下载流
            analyseService.exportPlayerRule(act,out);
            //正常不会执行到return
            return JsonResult(-5,"正常返回");
        } catch(Exception e){
            e.printStackTrace();
            //报错应该给前端一个返回
            return JsonResult(-5,"系统错误");
        }
    }

    //获取打分原始数据
    @RequestMapping(value = "/excel/rawdata",method = RequestMethod.GET)
    public String downRawData(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        Activity act=activityService.getActivity(activityid);
        //设置返回格式,注意微信需要这样设置contenttype
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        try{
            //打开输出流
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_原始数据表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
                //报错应该给前端一个返回
                return JsonResult(-5,"系统错误");
            }
            //导出表并返回下载流
            analyseService.exportRawData(act,out);
            //正常不会执行到return
            return JsonResult(-5,"正常返回");
        } catch(Exception e){
            e.printStackTrace();
            //报错应该给前端一个返回
            return JsonResult(-5,"系统错误");
        }
    }
}
