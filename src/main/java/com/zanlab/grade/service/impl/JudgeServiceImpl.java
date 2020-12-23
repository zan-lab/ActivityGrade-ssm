package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.GradeDao;
import com.zanlab.grade.dao.JudgeDao;
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
           // Activity activity=judge.getActivity();
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
            marks+=grade.getRule1()+grade.getRule2()+grade.getRule3()+grade.getRule4()+grade.getRule5()+grade.getRule6()+grade.getRule7()+grade.getRule8()+grade.getRule9();
            grade.setPlayerscore(marks);
            gradeDao.save(grade);
            playerService.updateAverage(grade.getPlayerid());
            return true;
        }
        return false;

    }

    @Override
    public Boolean hasGrade(Integer id) {
        return gradeDao.findById(id)!=null;
    }

    @Override
    public Boolean updateGrade(Grade grade) {
        Grade g=gradeDao.findById(grade.getId());
        combineSydwCore(grade,g);
        double marks=0.00;
        marks+=grade.getRule1()+grade.getRule2()+grade.getRule3()+grade.getRule4()+grade.getRule5()+grade.getRule6()+grade.getRule7()+grade.getRule8()+grade.getRule9();
        grade.setPlayerscore(marks);
        gradeDao.update(grade);
        playerService.updateAverage(grade.getPlayerid());
        return true;
    }

    @Override
    public List<Player> getUnjudgedPlayerList(Integer judgeid) {
        Judge judge=judgeDao.findById(judgeid);
        List<Player>allPlayerList=playerService.getListByActivityid(judge.getActivityid());
        List<Player>judgedList=getJudgedPlayerList(judgeid);
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
        return gradeDao.getListByJudgeid(judgeid);
    }
}
