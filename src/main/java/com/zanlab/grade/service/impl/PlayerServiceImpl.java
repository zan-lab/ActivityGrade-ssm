package com.zanlab.grade.service.impl;

import com.zanlab.grade.domain.Player;
import com.zanlab.grade.domain.Rule;
import com.zanlab.grade.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
    @Override
    public List<Rule> getListByActivityid(Integer activityid) {
        return null;
    }

    @Override
    public Boolean createPlayer(Player player) {
        return null;
    }

    @Override
    public Boolean updatePlayer(Player player) {
        return null;
    }

    @Override
    public Boolean deletePlayer(Integer id) {
        return null;
    }

    @Override
    public Boolean hasPlayer(Integer id) {
        return null;
    }
}
