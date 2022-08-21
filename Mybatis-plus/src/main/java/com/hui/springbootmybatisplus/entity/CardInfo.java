package com.hui.springbootmybatisplus.entity;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-18 22:28
 */
@Data
@TableName("card_info")
public class CardInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer cardNum;
    private String cardName;
    //额度
    private Integer quota;
    //还款日期
    private Integer billingDate;
    //相对 出账后N天还款
    private Long longestRepayment;
    //固定还款日期
    private Integer fixedRepayment;

}
