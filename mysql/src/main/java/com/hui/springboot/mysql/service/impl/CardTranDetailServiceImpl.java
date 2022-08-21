package com.hui.springboot.mysql.service.impl;

import com.hui.springboot.mysql.dao.CardTranDetailDao;
import com.hui.springboot.mysql.po.CardTranDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTranDetailServiceImpl {

    @Autowired
    private CardTranDetailDao tranDetailDao;


    public CardTranDetail getOne() {
        return tranDetailDao.selectByPrimaryKey(1);

    }


}
