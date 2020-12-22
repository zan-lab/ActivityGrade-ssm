package com.zanlab.grade.service;


import com.zanlab.grade.domain.Player;

import java.util.List;

public interface PlayerService {
    public List<Player> getListByActivityid(Integer activityid);

    public Boolean createPlayer(Player player);

    public Boolean updatePlayer(Player player);

    public Boolean deletePlayer(Integer id);

    public Boolean hasPlayer(Integer id);

    public void updateAverage(Integer id);


}
