package com.hui.springboot.mysql.dao;


import com.hui.springboot.mysql.po.CardTranDetail;
import org.apache.ibatis.annotations.Mapper;


public interface CardTranDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CardTranDetail record);

    int insertSelective(CardTranDetail record);

    CardTranDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardTranDetail record);

    int updateByPrimaryKey(CardTranDetail record);
}