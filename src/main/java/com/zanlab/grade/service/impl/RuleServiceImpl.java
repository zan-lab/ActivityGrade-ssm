package com.zanlab.grade.service.impl;

import com.zanlab.grade.dao.RuleDao;
import com.zanlab.grade.domain.Rule;
import com.zanlab.grade.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zanlab.grade.utils.ObjectCombine.combineSydwCore;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;
    @Override
    public List<Rule> getListByActivityid(Integer activityid) {
        return ruleDao.findListByActivityid(activityid);
    }

    @Override
    public Boolean createRule(Rule rule) {
        return ruleDao.save(rule)==1;
    }

    @Override
    public Boolean updateRule(Rule rule) {
        Rule r=ruleDao.findById(rule.getId());
        combineSydwCore(rule,r);
        return ruleDao.update(r)==1;
    }

    @Override
    public Boolean deleteRule(Integer id) {
        return ruleDao.delete(id)==1;
    }

    @Override
    public Boolean hasRule(Integer id) {
        return ruleDao.findById(id)!=null;
    }
}
