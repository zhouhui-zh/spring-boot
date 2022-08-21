package com.hui.springboot.mysql.service.impl;

import com.hui.springboot.mysql.dao.CardInfoDao;
import com.hui.springboot.mysql.po.CardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardInfoServiceImpl {

    @Autowired
    private CardInfoDao cardInfoDao;


    public CardInfo getOne() {
        return cardInfoDao.selectByPrimaryKey(1);

    }


}
