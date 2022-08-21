package com.hui.springboot.mysql.service;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import com.alibaba.fastjson.JSON;
import com.hui.springboot.mysql.dao.CardInfoDao;
import com.hui.springboot.mysql.dao.RepaymentPlanDao;
import com.hui.springboot.mysql.po.CardInfo;
import com.hui.springboot.mysql.po.RepaymentPlan;
import com.hui.springboot.mysql.utils.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DueDateService {
    private final Logger logger = LoggerFactory.getLogger(DueDateService.class);
    private static final String pattern_day = "yyyyMMdd";
    private static final String pattern_month = "yyyyMM";
    private static final SimpleDateFormat formatter_month = new SimpleDateFormat(pattern_month);
    private static final SimpleDateFormat formatter_day = new SimpleDateFormat(pattern_day);

    @Autowired
    private CardInfoDao cardInfoDao;
    @Autowired
    private RepaymentPlanDao repaymentPlanDao;

    @PostConstruct
    public void init() {
        List<CardInfo> list = cardInfoDao.getAll();
        list.stream().forEach(cardInfo -> {
            System.out.println(JSON.toJSONString(cardInfo));
            createPlan(cardInfo);
        });

    }

    public void createPlan(CardInfo cardInfo) {
        String month = getMonth();
        RepaymentPlan repaymentPlan = repaymentPlanDao.getOneByMonthCardId(month, cardInfo.getId());
        if (repaymentPlan != null) {
            return;
        }
        repaymentPlan = repaymentPlanDao.getLastByCardId(cardInfo.getId());
        if (repaymentPlan == null) {//空
            createPlanItem(cardInfo, new Date());
        } else {//没有当月
            String nextMonth = repaymentPlan.getMonth();
            try {
                Date startMonth = DateUtils.parseDate(nextMonth, pattern_month);
                startMonth = DateUtils.addMonths(startMonth, 1);
                Date lastMonth = DateUtils.parseDate(month, pattern_month);
                lastMonth = DateUtils.addMonths(lastMonth, 1);
                while (lastMonth.after(startMonth)) {
                    nextMonth = formatter_day.format(startMonth);
                    System.out.println(String.format("%s信用卡新增%s月还款计划", cardInfo.getCardName(), nextMonth));

                    logger.info("{} 信用卡 {} 新增{} 还款 计划 ", cardInfo.getCardName(), cardInfo.getCardNum(), nextMonth);
                    createPlanItem(cardInfo, startMonth);

                    startMonth = DateUtils.addMonths(startMonth, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    public void createPlanItem(CardInfo cardInfo, Date month) {
        if (month == null) {
            month = new Date();
        }
        RepaymentPlan repaymentPlan = new RepaymentPlan();
        repaymentPlan.setCardId(cardInfo.getId());
        Integer billingDate = cardInfo.getBillingDate();
        Integer fixedRepayment = cardInfo.getFixedRepayment();
        Integer longestRepayment = cardInfo.getLongestRepayment();


        Date now = month;
        now = DateUtils.setDays(now, billingDate);
        repaymentPlan.setBillDate(formatter_day.format(now));
        repaymentPlan.setMonth(formatter_month.format(month));

        Date nextRepayment = null;
        if (null != fixedRepayment) {//固定还款日
            nextRepayment = DateUtils.setDays(now, fixedRepayment);
            if (billingDate > fixedRepayment) {// //下月
                nextRepayment = DateUtils.addMonths(nextRepayment, 1);
            }
        } else if (null != longestRepayment) {//最长还款天数
            nextRepayment = DateUtils.addDays(now, longestRepayment);
        } else {
            return;
        }

        String nextRepaymentStr = formatter_day.format(nextRepayment);
        //最后还款日
        repaymentPlan.setLatestRepaymentDate(nextRepaymentStr);
        logger.info("{} 信用卡 {} 的出账单日期 {} 最后还款日期 {}", cardInfo.getCardName(), cardInfo.getCardNum(), repaymentPlan.getBillDate(),
                repaymentPlan.getLatestRepaymentDate());
        repaymentPlanDao.insert(repaymentPlan);
    }

    public String getMonth() {
        return formatter_month.format(new Date());
    }


}
