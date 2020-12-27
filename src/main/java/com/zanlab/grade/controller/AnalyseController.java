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
    //获取选手-评委表
    @RequestMapping(value = "/excel/playerjudge",method = RequestMethod.GET)
    public String downPlayerJudge(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        Activity act=activityService.getActivity(activityid);
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_选手得分表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            analyseService.exportPlayerJudge(act,out);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    //获取选手详细得分表
    @RequestMapping(value = "/excel/playerrule",method = RequestMethod.GET)
    public String downPlayerRule(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        Activity act=activityService.getActivity(activityid);
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_选手详细得分表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            analyseService.exportPlayerRule(act,out);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    //获取打分原始数据
    @RequestMapping(value = "/excel/rawdata",method = RequestMethod.GET)
    public String downRawData(HttpServletResponse response, @RequestParam("activityid") Integer activityid){
        if(!activityService.hasActivity(activityid))return JsonResult(-1,"活动不存在");
        Activity act=activityService.getActivity(activityid);
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(act.getName()+"_原始数据表"+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            analyseService.exportRawData(act,out);
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }
}
