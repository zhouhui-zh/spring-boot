package com.hui.springboot.mysql.controller;

import com.hui.springboot.mysql.po.RepaymentPlan;
import com.hui.springboot.mysql.service.impl.RepaymentPlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/repaymentPlan")
public class RepaymentPlanController {

    @Autowired
    private RepaymentPlanServiceImpl cardDateListService;

    @ResponseBody
    @RequestMapping("/getOne")
    public RepaymentPlan getOne() {
        return cardDateListService.getOne();
    }
}
