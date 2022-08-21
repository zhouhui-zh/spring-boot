package com.hui.springboot.mysql.dao;

import com.hui.springboot.mysql.po.CardTranDetail;
import com.hui.springboot.mysql.po.RepaymentPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepaymentPlanDao {

    int insert(RepaymentPlan record);

    List<RepaymentPlan> getListByMonth(@Param("month") String month);

    RepaymentPlan getLastByCardId(@Param("cardId") Long cardId);

    RepaymentPlan getOneByMonthCardId(@Param("month") String month, @Param("cardId") Long cardId);
}
