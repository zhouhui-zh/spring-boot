package com.hui.springboot.mysql.controller;

import com.hui.springboot.mysql.po.CardInfo;
import com.hui.springboot.mysql.po.CardTranDetail;
import com.hui.springboot.mysql.service.impl.CardInfoServiceImpl;
import com.hui.springboot.mysql.service.impl.CardTranDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/cardTranDetail")
public class CardTranDetailController {
    @Autowired
    private CardTranDetailServiceImpl cardTranDetailService;

    @ResponseBody
    @RequestMapping("/getOne")
    public CardTranDetail getOne() {
        return cardTranDetailService.getOne();
    }
}
