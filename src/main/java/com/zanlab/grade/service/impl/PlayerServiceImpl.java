package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.GradeDao;
import com.zanlab.grade.dao.PlayerDao;
import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private GradeDao gradeDao;
    @Override
    public List<Player> getListByActivityid(Integer activityid) {
        return playerDao.findListByActivityid(activityid);
    }

    @Override
    public Boolean createPlayer(Player player) {
        return playerDao.save(player)==1;
    }

    @Override
    public Boolean updatePlayer(Player player) {
        Player p=playerDao.findById(player.getId());
        combineSydwCore(player,p);
        return playerDao.update(p)==1;
    }

    @Override
    public Boolean deletePlayer(Integer id) {
        return playerDao.delete(id)==1;
    }

    @Override
    public Boolean hasPlayer(Integer id) {
        return playerDao.findById(id)!=null;
    }

    /**
     * 计算用户的平均值，写入字段
     * @param id 用户id
     */
    @Override
    public void updateAverage(Integer id) {
        Player player=playerDao.findById(id);
        //先获取所有相关打分
        List<Grade> gradeList= gradeDao.findListByPlayerid(id);
        //更新选手的得分
        //拿到成绩列表
        List<Double> scorelist=new ArrayList();
        for(Grade grade:gradeList){
            scorelist.add(grade.getPlayerscore());
        }
        //计算普通平均值
        player.setScore(calculateAverage(scorelist,0));
        //计算截尾平均值
        player.setFairscore(calculateAverage(scorelist,1));
        playerDao.updateWithScore(player);
    }

    /**
     * 计算grade列表的成绩平均值，返回精度0.01的平均
     * @param scoreList 分数列表
     * @param fairweight 截取多少尾巴
     * @return 0.01精度的平均值
     */
   private double calculateAverage(List<Double> scoreList,int fairweight){
       int num=scoreList.size();
       //如果数量不够就不截尾巴
       if(num<=fairweight*2){
           return calculateAverage(scoreList,0);
       }
       //排序
       Collections.sort(scoreList);
       //一直截尾巴
       while(fairweight!=0){
           scoreList.remove(0);
           scoreList.remove(scoreList.size()-1);
           fairweight--;
       }
       double average=0.00d;
       //计算平均值
       for(Double e:scoreList){
           average+=e/scoreList.size();
       }
       //格式化输出
       DecimalFormat df = new DecimalFormat("######0.00");
       return Double.parseDouble(df.format(average));
   }

}
