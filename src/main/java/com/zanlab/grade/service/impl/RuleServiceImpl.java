package com.zanlab.grade.service.impl;

import com.zanlab.grade.domain.Rule;
import com.zanlab.grade.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
    @Override
    public List<Rule> getListByActivityid(Integer activityid) {
        return null;
    }

    @Override
    public Boolean createRule(Rule rule) {
        return null;
    }

    @Override
    public Boolean updateRule(Rule rule) {
        return null;
    }

    @Override
    public Boolean deleteRule(Integer id) {
        return null;
    }

    @Override
    public Boolean hasRule(Integer id) {
        return null;
    }
}
