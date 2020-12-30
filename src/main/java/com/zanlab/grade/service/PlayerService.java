package com.zanlab.grade.service;


import com.zanlab.grade.domain.Player;

import java.util.List;

public interface PlayerService {
    //根据活动id获取活动选手列表
    List<Player> getListByActivityid(Integer activityid);

    //活动创建选手
    Boolean createPlayer(Player player);

    //活动更新选手基本信息
    Boolean updatePlayer(Player player);

    //活动删除选手
    Boolean deletePlayer(Integer id);

    //判断选手是否存在
    Boolean hasPlayer(Integer id);

    //更新选手基本信息
    void updateAverage(Integer id);


}
