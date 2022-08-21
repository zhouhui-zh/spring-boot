package com.hui.springboot.mysql.service.impl;

import com.hui.springboot.mysql.dao.RepaymentPlanDao;
import com.hui.springboot.mysql.po.RepaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentPlanServiceImpl {
    @Autowired
    private RepaymentPlanDao repaymentPlanDao;

    public RepaymentPlan getOne() {
        return null;
    }


}
