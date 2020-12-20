package com.zanlab.grade.service;

import com.zanlab.grade.domain.Rule;

import java.util.List;

public interface RuleService {
    public List<Rule>getListByActivityid(Integer activityid);

    public Boolean createRule(Rule rule);

    public Boolean updateRule(Rule rule);

    public Boolean deleteRule(Integer id);

    public Boolean hasRule(Integer id);
}
