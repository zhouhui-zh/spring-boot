package com.hui.springboot.mysql.dao;

import com.hui.springboot.mysql.po.CardInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CardInfoDao {


    CardInfo selectByPrimaryKey(@Param("id") Integer id);


    List<CardInfo> getAll();
}