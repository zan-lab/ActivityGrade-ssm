package com.zanlab.grade.service;

import com.zanlab.grade.domain.Rule;

import java.util.List;

public interface RuleService {
    //规则增删改查以及判断活动是否存在
    List<Rule>getListByActivityid(Integer activityid);

    Boolean createRule(Rule rule);

    Boolean updateRule(Rule rule);

    Boolean deleteRule(Integer id);

    Boolean hasRule(Integer id);
}
