package com.hui.springboot.mysql.po;

public class CardInfo {
    private Long id;

    private String cardNum;
    private String cardName;
    //额度
    private Integer quota;
    //账单日
    private Integer billingDate;
    //最长还款天数
    private Integer longestRepayment;
    //固定还款日
    private Integer fixedRepayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Integer billingDate) {
        this.billingDate = billingDate;
    }

    public Integer getLongestRepayment() {
        return longestRepayment;
    }

    public void setLongestRepayment(Integer longestRepayment) {
        this.longestRepayment = longestRepayment;
    }

    public Integer getFixedRepayment() {
        return fixedRepayment;
    }

    public void setFixedRepayment(Integer fixedRepayment) {
        this.fixedRepayment = fixedRepayment;
    }
}