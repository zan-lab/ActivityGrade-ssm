package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.GradeDao;
import com.zanlab.grade.dao.PlayerDao;
import com.zanlab.grade.domain.Grade;
import com.zanlab.grade.domain.Player;
import com.zanlab.grade.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.zanlab.grade.utils.CommonUtils.calculateAverage;
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


}
