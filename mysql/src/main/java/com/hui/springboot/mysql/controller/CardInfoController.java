package com.hui.springboot.mysql.controller;

import com.hui.springboot.mysql.po.CardInfo;
import com.hui.springboot.mysql.service.impl.CardInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/cardInfo")
public class CardInfoController {

    @Autowired
    private CardInfoServiceImpl cardInfoService;

    @ResponseBody
    @RequestMapping("/getOne")
    public CardInfo getOne() {
        return cardInfoService.getOne();
    }
}
