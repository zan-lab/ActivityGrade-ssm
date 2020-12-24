package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.GradeDao;
import com.zanlab.grade.dao.JudgeDao;
import com.zanlab.grade.dao.PlayerDao;
import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Judge;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.JudgeService;
import com.zanlab.grade.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

@Service("judgeService")
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    private JudgeDao judgeDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerDao playerDao;

    @Override
    public List<Judge> getJudgeListByActivityid(Integer activityid) {
        return judgeDao.findListByActivityid(activityid);
    }

    @Override
    public Boolean addJudge(Judge judge) {
        return judgeDao.save(judge)==1;
    }

    @Override
    public Boolean judge(Grade grade) {
        Judge judge=judgeDao.findById(grade.getJudgeid());
        if(judge!=null){
            grade.setActivityid(judge.getActivityid());
            //定义计算总分的变量
            double marks=0.00;
//            int rulecount=activity.getRules().size();
//            Class<?> gradeClass = Grade.class;//获取对应方法所属对象类
//            try {
//                //执行getRule1到getRulerulecount
//                for(int i=0;i<rulecount;i++){
//                    Method getMethod = gradeClass.getDeclaredMethod("getRule"+(i+1));//通过String获取方法
//                    Object rulescore=getMethod.invoke(grade);
//                    //如果真的遇到空的直接返回
//                    if(rulescore==null)return false;
//                    else marks+=(double)rulescore;
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
            //速度比反射快
            if(grade.getRule1()==null)return false;
            if(grade.getRule2()==null)grade.setRule2(0.00);
            if(grade.getRule3()==null)grade.setRule3(0.00);
            if(grade.getRule4()==null)grade.setRule4(0.00);
            if(grade.getRule5()==null)grade.setRule5(0.00);
            if(grade.getRule6()==null)grade.setRule6(0.00);
            if(grade.getRule7()==null)grade.setRule7(0.00);
            if(grade.getRule8()==null)grade.setRule8(0.00);
            if(grade.getRule9()==null)grade.setRule9(0.00);
            if(grade.getRule10()==null)grade.setRule10(0.00);
            marks+=grade.getRule1()+grade.getRule2()+grade.getRule3()+grade.getRule4()+grade.getRule5()+grade.getRule6()+grade.getRule7()+grade.getRule8()+grade.getRule9()+grade.getRule10();
            grade.setPlayerscore(marks);
            gradeDao.save(grade);
            playerService.updateAverage(grade.getPlayerid());
            return true;
        }
        return false;

    }

    @Override
    public Boolean hasGrade(Integer judgeid,Integer playerid) {
        return gradeDao.findByJidandPid(judgeid,playerid)!=null;
    }

    @Override
    public Boolean updateGrade(Grade grade) {
        Grade g=gradeDao.findById(grade.getId());
        combineSydwCore(grade,g);
        double marks=0.00;
        marks+=g.getRule1()+g.getRule2()+g.getRule3()+g.getRule4()+g.getRule5()+g.getRule6()+g.getRule7()+g.getRule8()+g.getRule9()+g.getRule10();
        g.setPlayerscore(marks);
        gradeDao.update(g);
        playerService.updateAverage(grade.getPlayerid());
        return true;
    }

    @Override
    public List<Player> getUnjudgedPlayerList(Integer judgeid) {
        //先找到judge
        Judge judge=judgeDao.findById(judgeid);
        //找到活动的所有player
        List<Player>allPlayerList=playerService.getListByActivityid(judge.getActivityid());
        //获取已经打分的player
        List<Player>judgedList=getJudgedPlayerList(judgeid);
        //获取所有打分的player的id list
        List<Integer>judgedPlayeridList=new ArrayList<>();
        for(Player player:judgedList){
            judgedPlayeridList.add(player.getId());
        }
        List<Player>unjudgedList=new ArrayList<>();
        for(Player player: allPlayerList){
            if(!judgedPlayeridList.contains(player.getId())){
                unjudgedList.add(player);
            }
        }
        return unjudgedList;
    }

    @Override
    public List<Player> getJudgedPlayerList(Integer judgeid) {
        //获取评委打过的所有成绩
        List<Grade> gradeList= gradeDao.getListByJudgeid(judgeid);
        //把每个player拿出来
        List<Player>res=new ArrayList<>();
        for(Grade grade:gradeList){
            res.add(playerDao.findById(grade.getPlayerid()));
        }
        return res;
    }


    //通过用户id和活动id查找是否存在评委了
    @Override
    public Judge getByUserandActivity(Integer userid, Integer activityid) {
        return judgeDao.findByUserandActivity(userid,activityid);
    }

    //判断是否存在评委了
    @Override
    public Boolean hasJudge(Integer userid, Integer activityid) {
        return getByUserandActivity(userid,activityid)!=null;
    }

    @Override
    public Boolean hasJudge(Integer judgeid) {
        return judgeDao.findById(judgeid)!=null;
    }
}
